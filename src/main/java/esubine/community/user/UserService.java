package esubine.community.user;

import esubine.community.auth.TokenEntity;
import esubine.community.auth.TokenRepository;
import esubine.community.db.user.UserEntity;
import esubine.community.db.user.UserRepository;
import esubine.community.exception.AuthException;
import esubine.community.exception.DuplicatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    public UserEntity createUser(CreateUserRequest createUserRequest) {
        if (userRepository.findByLoginId(createUserRequest.getLoginId()) != null) {
            throw new DuplicatedException("아이디 중복");
        }
        if (userRepository.findByNickname(createUserRequest.getNickname()) != null) {
            throw new DuplicatedException("닉네임 중복");
        }

        return userRepository.save(createUserRequest.toEntity());
    }

    public Optional<UserEntity> getUserInfo(String tokenInput, Long id) {
        Long userId = getUserIdByToken(tokenInput);
        if(userId.equals(id)) {
            Optional<UserEntity> user = userRepository.findById(userId);
            return user;
        }
        throw new AuthException("사용자 정보 일치하지 않습니다.");
    }

    private Long getUserIdByToken(String tokenValue) {
        TokenEntity token = tokenRepository.findByToken(tokenValue.substring("Bearer ".length()));
        if (token == null) {
            throw new AuthException("권한이 없습니다.");
        }
        Long userId = token.getUserId();
        return userId;
    }


}

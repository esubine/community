package esubine.community.user;

import esubine.community.EmptyResponse;
import esubine.community.auth.model.TokenEntity;
import esubine.community.auth.model.TokenRepository;
import esubine.community.user.model.UserEntity;
import esubine.community.user.model.UserRepository;
import esubine.community.exception.AuthException;
import esubine.community.exception.DuplicatedException;
import esubine.community.exception.MisMatchException;
import esubine.community.user.dto.CreateUserRequest;
import esubine.community.user.dto.UpdateNicknameRequest;
import esubine.community.user.dto.UpdatePasswordRequest;
import esubine.community.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public UserResponse getUserInfo(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new AuthException("존재하지않는 유저입니다."));
        return new UserResponse(user);
    }

    private Long getUserIdByToken(String tokenInput) {

        TokenEntity token = tokenRepository.findByToken(tokenInput.substring("Bearer ".length()));
        System.out.println("token = " + token);
        if (token == null) {
            throw new AuthException("권한이 없습니다.");
        }
        Long userId = token.getUserId();
        return userId;
    }

    public UserResponse updateNickname(Long userId, UpdateNicknameRequest updateNicknameRequest) {
        //토큰 확인 - 토큰에 유저아이디 들어온 아이디가 일치하는지 - 유저엔티티.getnickname해서 닉네임 중복확인 - 닉네임 업데이트
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AuthException("존재하지않는 유저입니다."));

        if (userRepository.findByNickname(updateNicknameRequest.getNickname()) == null) {
            user.setNickname(updateNicknameRequest.getNickname());
            userRepository.save(user);
        }

        return new UserResponse(user);
    }

    @Transactional
    public EmptyResponse deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AuthException("존재하지않는 유저입니다."));
        tokenRepository.deleteAllByUserId(user.getId());
        userRepository.delete(user);
        return null;
    }

    public EmptyResponse updatePassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AuthException("존재하지않는 유저입니다."));
        if (user.getLoginPassword().equals(updatePasswordRequest.getPresentPassword())) {
            user.setLoginPassword(updatePasswordRequest.getNewPassword());
            userRepository.save(user);

        } else {
            throw new MisMatchException("기존 비밀번호가 일치하지 않습니다.");
        }
        return new EmptyResponse();
    }
}

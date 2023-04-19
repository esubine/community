package esubine.community.auth;

import esubine.community.auth.model.TokenEntity;
import esubine.community.auth.model.TokenRepository;
import esubine.community.user.model.UserEntity;
import esubine.community.user.model.UserRepository;
import esubine.community.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public String generatedToken(String id, String password) {
        UserEntity user = loginUser(id, password);
        if (user == null) throw new AuthException("사용자 정보가 없습니다.");
        else if (user.isDelete()) throw new AuthException("탈퇴한 회원입니다.");

        String createdToken = UUID.randomUUID().toString().replace("-", "");
        TokenEntity token = new TokenEntity(createdToken, user.getId());
        tokenRepository.save(token);
        return createdToken;
    }

    private UserEntity loginUser(String id, String password) {
        return userRepository.findByLoginIdAndLoginPassword(id, password);
    }

}

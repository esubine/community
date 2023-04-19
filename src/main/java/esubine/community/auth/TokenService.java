package esubine.community.auth;

import esubine.community.auth.model.TokenEntity;
import esubine.community.auth.model.TokenRepository;
import esubine.community.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Long tokenReturnUserId(String tokenInput) {
        TokenEntity token = tokenRepository.findByToken(tokenInput.substring("Bearer ".length()));

        if (token == null) throw new AuthException("유효하지 않는 토큰 입니다.");
        return token.getUserId();


//        TokenEntity token = tokenRepository.findByToken(tokenInput.substring("Bearer ".length()));
//        System.out.println("token = " + token);
//        if (token == null) {
//            throw new AuthException("권한이 없습니다.");
//        }
//        Long userId = token.getUserId();
//        return userId;
    }

}

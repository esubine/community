package esubine.community.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Long tokenReturnUserId(String tokenInput){
        TokenEntity token = tokenRepository.findByToken(tokenInput.substring("Bearer ".length()));
        return token.getUserId();
    }

}

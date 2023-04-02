package esubine.community.auth;

import esubine.community.auth.dto.LoginRequest;
import esubine.community.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public TokenResponse loginUser(@RequestBody LoginRequest loginRequest){
        String token = authService.generatedToken(loginRequest.getId(), loginRequest.getPassword());
        return new TokenResponse(token);
    }
}

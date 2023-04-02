package esubine.community.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthInfo {
    private Long userId;
    private String token;
}

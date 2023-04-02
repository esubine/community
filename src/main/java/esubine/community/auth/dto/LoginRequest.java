package esubine.community.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String id;
    private String password;
}

package esubine.community.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {
    private String presentPassword;
    private String newPassword;
}

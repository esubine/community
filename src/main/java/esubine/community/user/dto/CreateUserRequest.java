package esubine.community.user.dto;

import esubine.community.user.model.UserEntity;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String realName;
    private String nickname;
    private String loginId;
    private String loginPassword;

    public UserEntity toEntity(){
        return UserEntity.of(
                this.realName,
                this.nickname,
                this.loginId,
                this.loginPassword
        );
    }
}

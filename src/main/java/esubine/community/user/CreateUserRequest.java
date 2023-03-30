package esubine.community.user;

import esubine.community.db.user.UserEntity;
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

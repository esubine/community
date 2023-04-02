package esubine.community.user;

import esubine.community.db.user.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final String realName;
    private final String nickname;
    private final String loginId;

    public UserResponse(UserEntity user){
        this.realName = user.getRealName();
        this.nickname = user.getNickname();
        this.loginId = user.getLoginId();
    }
}

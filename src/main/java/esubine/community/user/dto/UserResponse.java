package esubine.community.user.dto;

import esubine.community.user.model.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final Long id;
    private final String realName;
    private final String nickname;
    private final String loginId;

    public UserResponse(UserEntity user){
        this.id = user.getId();
        this.realName = user.getRealName();
        this.nickname = user.getNickname();
        this.loginId = user.getLoginId();
    }
}

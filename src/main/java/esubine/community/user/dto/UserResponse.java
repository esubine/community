package esubine.community.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import esubine.community.user.model.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final Long id;
    private final String realName;
    private final String nickname;
    private final String loginId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Set<Long> userBadges;

    public UserResponse(UserEntity user){
        this.id = user.getId();
        this.realName = user.getRealName();
        this.nickname = user.getNickname();
        this.loginId = user.getLoginId();
        this.userBadges = user.getBadgeId();
    }
}


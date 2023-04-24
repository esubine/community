package esubine.community.user.dto;

import esubine.community.badge.model.BadgeEntity;
import esubine.community.user.model.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final Long id;
    private final String realName;
    private final String nickname;
    private final String loginId;

    private final Set<BadgeInfoResponse> badgeInfo;


    public UserResponse(UserEntity user){
        this.id = user.getId();
        this.realName = user.getRealName();
        this.nickname = user.getNickname();
        this.loginId = user.getLoginId();
        this.badgeInfo = user.getBadges().stream()
                .map(BadgeInfoResponse::new)
                .collect(Collectors.toSet());
    }
}


package esubine.community.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import esubine.community.badge.model.BadgeEntity;
import esubine.community.user.model.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class BadgeInfoResponse {

    private final Long id;

    private final String badgeName;

//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private final Set<UserBadgeEntity> badges;

    public BadgeInfoResponse (BadgeEntity badge) {
        this.id = badge.getBadgeId();
        this.badgeName = badge.getName();
    }

//    public Set<BadgeInfoResponse> responses(BadgeEntity badge){
//        Set<BadgeInfoResponse> result = new HashSet<>();
//        result.add()
//    }

}

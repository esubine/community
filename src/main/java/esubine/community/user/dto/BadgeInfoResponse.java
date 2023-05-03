package esubine.community.user.dto;

import esubine.community.badge.model.BadgeEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BadgeInfoResponse {

    private final Long id;

    private final String badgeName;

    public BadgeInfoResponse (BadgeEntity badge) {
        this.id = badge.getBadgeId();
        this.badgeName = badge.getName();
    }


}

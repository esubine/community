package esubine.community.badge;

import com.fasterxml.jackson.annotation.JsonInclude;
import esubine.community.badge.model.UserBadgeEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class BadgeResponse {

    private final Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Set<Long> userIdLists;

    public BadgeResponse(Set<UserBadgeEntity> userBadge, Long badgeId) {
        this.id = badgeId;
        this.userIdLists = userBadge.stream()
                .map((e) -> e.getUser().getId())
                .collect(Collectors.toSet());
    }
}

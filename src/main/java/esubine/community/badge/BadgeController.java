package esubine.community.badge;

import esubine.community.badge.model.UserBadgeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/badge")
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping
    public BadgeResponse searchUserByBadgeId(
            @RequestParam(value = "badgeId") Long badgeId
    ) {
        Set<UserBadgeEntity> userBadge = badgeService.searchUser(badgeId);
        return new BadgeResponse(userBadge, badgeId);
    }
}

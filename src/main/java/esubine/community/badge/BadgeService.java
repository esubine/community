package esubine.community.badge;

import esubine.community.badge.model.BadgeEntity;
import esubine.community.badge.model.BadgeRepository;
import esubine.community.badge.model.UserBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final UserBadgeRepository userBadgeRepository;
    private final BadgeRepository badgeRepository;

    public void refreshBadge(Long userId) {
        Set<BadgeEntity> badgeEntities = badgeRepository.getByBadgeId(12l);
        System.out.println("badgeEntities = " + badgeEntities);
    }


}

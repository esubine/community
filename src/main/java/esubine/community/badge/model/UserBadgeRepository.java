package esubine.community.badge.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserBadgeRepository extends JpaRepository<UserBadgeEntity, Long> {
    @Query("SELECT ub.badge.badgeId FROM UserBadgeEntity ub " +
            "WHERE ub.user.id=:userId ")
    Set<UserBadgeEntity> userBadges(Long userId);


}

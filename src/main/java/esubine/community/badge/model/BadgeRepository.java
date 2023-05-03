package esubine.community.badge.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface BadgeRepository extends JpaRepository<BadgeEntity, Long> {

    @Query("SELECT b FROM UserBadgeEntity ub " +
            "RIGHT JOIN ub.badge b ON ub.user.id=:userId " +
            "WHERE b.badgeId IS NULL ")
    Set<BadgeEntity> getByUserId(Long userId);

    @Query("SELECT ub FROM UserBadgeEntity ub " +
            "WHERE ub.badge.badgeId=:badgeId ")
    Set<UserBadgeEntity> searchUserByBadgeId(Long badgeId);

}

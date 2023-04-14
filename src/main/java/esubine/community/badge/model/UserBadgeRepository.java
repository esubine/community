package esubine.community.badge.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBadgeRepository extends JpaRepository<UserBadgeEntity, Long> {
}

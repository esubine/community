package esubine.community.user.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlockUserRepository extends JpaRepository<BlockUserEntity, Long> {
    Optional<BlockUserEntity> findByRequesterIdAndTargetId(Long requesterId, Long targetId);



}

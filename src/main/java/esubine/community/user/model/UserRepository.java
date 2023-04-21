package esubine.community.user.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNickname(String nickname);
    UserEntity findByLoginId(String loginId);
    UserEntity findByLoginIdAndLoginPassword(String loginId, String loginPassword);

    @Query("SELECT u FROM UserEntity u " +
            "LEFT JOIN FETCH u.userBadges " +
            "WHERE u.id=:userId ")
    Optional<UserEntity> getByUserId(Long userId);

}

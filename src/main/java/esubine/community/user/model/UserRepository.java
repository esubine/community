package esubine.community.user.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNickname(String nickname);
    UserEntity findByLoginId(String loginId);
    UserEntity findByLoginIdAndLoginPassword(String loginId, String loginPassword);

    @Query("SELECT u FROM UserEntity u " +
            "LEFT JOIN FETCH u.userBadges ub " +
            "LEFT JOIN FETCH ub.badge " +
            "WHERE u.id=:userId ")
    Optional<UserEntity> getByUserId(Long userId);

    @Query("SELECT u FROM UserEntity u " +
            "LEFT JOIN FETCH u.userBadges ub " +
            "LEFT JOIN FETCH ub.badge " +
            "WHERE u.id=:userId ")
    List<UserEntity> getByUser(List<Long> userId);


}

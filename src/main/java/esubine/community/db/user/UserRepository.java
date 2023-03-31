package esubine.community.db.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByNickname(String nickname);
    UserEntity findByLoginId(String loginId);

    UserEntity findByLoginIdAndLoginPassword(String loginId, String loginPassword);
}

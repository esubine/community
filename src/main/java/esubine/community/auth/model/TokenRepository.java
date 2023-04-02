package esubine.community.auth.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByToken(String token);

    @Modifying
    @Query(value = "delete from token where user_id=:userId", nativeQuery = true)
    void deleteAllByUserId(Long userId);
}

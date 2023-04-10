package esubine.community.board.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeInfoRepository extends JpaRepository<LikeInfoEntity, Long> {
    List<LikeInfoEntity> findAllByBoardIdAndUserId(Long boardId, Long userId);

    LikeInfoEntity findByBoardIdAndUserId(Long boardId, Long userId);

    @Modifying
    @Query("DELETE FROM LikeInfoEntity l " +
            "WHERE l=:entity ")
    int deleteAndGetResult(LikeInfoEntity entity);
}

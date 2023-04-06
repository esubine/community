package esubine.community.board.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesInfoRepository extends JpaRepository<LikesInfoEntity, Long> {
    List<LikesInfoEntity> findAllByBoardIdAndUserId(Long boardId, Long userId);

    LikesInfoEntity findByBoardIdAndUserId(Long boardId, Long userId);
}

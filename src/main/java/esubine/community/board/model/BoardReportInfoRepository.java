package esubine.community.board.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardReportInfoRepository extends JpaRepository<BoardReportInfoEntity, Long> {
    List<BoardReportInfoEntity> findAllByBoardIdAndUserId(Long boardId, Long userId);

    BoardReportInfoEntity findByBoardIdAndUserId(Long boardId, Long userId);
}

package esubine.community.board.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user " +
            "WHERE b.user.id=:userId ")
    List<BoardEntity> getByUserId(Pageable pageable, Long userId);

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user ")
    List<BoardEntity> getAll(Pageable pageable);

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user " +
            "WHERE b.boardId=:boardId ")
    Optional<BoardEntity> getByBoardId(Long boardId);

    @Query("UPDATE BoardEntity b " +
            "SET b.likeCount = b.likeCount+1 " +
            "WHERE b.boardId=:boardId ")
    @Modifying
    void increaseLikeCount(Long boardId);

    @Query("UPDATE BoardEntity b " +
            "SET b.likeCount = b.likeCount-1 " +
            "WHERE b.boardId=:boardId ")
    @Modifying
    void decreaseLikeCount(Long boardId);
}

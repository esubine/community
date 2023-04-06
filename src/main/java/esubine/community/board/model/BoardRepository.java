package esubine.community.board.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user " +
            "WHERE b.user.id=:userId ")
    List<BoardEntity> getByUserId(Long userId);

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user ")
    List<BoardEntity> getAll();

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user " +
            "WHERE b.boardId=:boardId ")
    Optional<BoardEntity> getByBoardId(Long boardId);




}

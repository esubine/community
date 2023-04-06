package esubine.community.comment.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c " +
            "LEFT JOIN FETCH c.user " +
            "WHERE c.boardId=:boardId ")
    List<CommentEntity> getAllByBoardId(Long boardId);

}

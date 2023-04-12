package esubine.community.comment.model;

import esubine.community.comment.dto.ChildCommentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c " +
            "LEFT JOIN FETCH c.user " +
            "LEFT JOIN FETCH c.children cc " +
            "WHERE c.boardId=:boardId " +
            "ORDER BY c.commentId ASC, cc.commentId ASC ")
    List<CommentEntity> getCommentAllByBoardId(Long boardId, Pageable pageable);

    @Query("SELECT c FROM CommentEntity c " +
            "LEFT JOIN FETCH c.user " +
            "WHERE c.user.id=:userId ")
    List<CommentEntity> getCommentByUserId(Long userId, Pageable pageable);

    @Query("SELECT c FROM CommentEntity c " +
            "LEFT JOIN FETCH c.user " +
            "LEFT JOIN FETCH c.parentCommentId " +
            "WHERE c.parentCommentId=:parentCommentId"
    )
    CommentEntity getByParentCommentId(Long parentCommentId);

    CommentEntity findCommentEntityByParentCommentId(Long parentCommentId);
}

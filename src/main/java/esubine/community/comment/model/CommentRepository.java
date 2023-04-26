package esubine.community.comment.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT c FROM CommentEntity c " +
            "WHERE c.commentId=:commentId " +
            "AND c.isDelete=false ")
    Optional<CommentEntity> getByCommentId(Long commentId);

    Optional<CommentEntity> findByCommentId(Long commentId);

    @Query("SELECT c FROM CommentEntity c " +
            "LEFT JOIN FETCH c.user " +
            "LEFT JOIN FETCH c.user.userBadges ub " +
            "LEFT JOIN FETCH ub.badge " +
            "LEFT JOIN FETCH c.children cc " +
            "WHERE c.boardId=:boardId " +
            "AND c.isDelete=false " +
            "ORDER BY c.commentId ASC, cc.commentId ASC ")
    List<CommentEntity> getCommentAllByBoardId(Long boardId, Pageable pageable);

    @Query("SELECT c FROM CommentEntity c " +
            "LEFT JOIN FETCH c.user " +
            "LEFT JOIN FETCH c.user.userBadges ub " +
            "LEFT JOIN FETCH ub.badge " +
            "INNER JOIN BoardEntity b on b.boardId = c.boardId and b.isDelete=false " +
            "WHERE c.user.id=:userId " +
            "AND c.isDelete=false ")

    List<CommentEntity> getCommentByUserId(Long userId, Pageable pageable);

    Long countByUserId(Long userId);
}

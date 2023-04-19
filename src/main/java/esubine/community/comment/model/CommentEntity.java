package esubine.community.comment.model;

import esubine.community.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "board_id")
    private Long boardId;

    @Column(name="parent_comment_id")
    private Long parentCommentId;

    @JoinColumn(name= "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Column(name="comment")
    private String comment;

    @OneToMany(mappedBy = "parentCommentId", fetch = FetchType.LAZY)
    private List<CommentEntity> children;

    @Column(name="is_delete")
    private boolean isDelete;

    public CommentEntity(Long userId, Long boardId, Long commentId, String comment){
        this.user = UserEntity.of(userId);
        this.boardId = boardId;
        this.parentCommentId = commentId;
        this.comment = comment;
    }
}

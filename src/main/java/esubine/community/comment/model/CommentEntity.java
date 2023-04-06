package esubine.community.comment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Column(name="user_id")
    private Long userId;

    @Column(name="comment")
    private String comment;

    public CommentEntity(Long userId, Long boardId, String comment){
        this.userId = userId;
        this.boardId = boardId;
        this.comment = comment;
    }
}

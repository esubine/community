package esubine.community.board.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "board_likes_info")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class LikesInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @Column(name="user_id")
    private Long userId;
    @Column(name="board_id")
    private Long boardId;
}

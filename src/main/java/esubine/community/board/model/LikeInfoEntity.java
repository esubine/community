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
@Table(name = "board_like_info")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class LikeInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_like_info_id")
    private Long boardLikeInfoId;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "board_id")
    private Long boardId;


    public LikeInfoEntity(Long userId, Long boardId) {
        this.userId = userId;
        this.boardId = boardId;
    }
}

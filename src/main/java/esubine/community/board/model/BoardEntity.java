package esubine.community.board.model;

import esubine.community.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "board")
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

//    @JoinColumn(name="category_id")
//    @ManyToOne(fetch=FetchType.LAZY)
//    private CategoryEntity category;

    @JoinColumn(name= "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name="likes")
    private int likes;

    @Column(name="reports")
    private int reports;

    public static BoardEntity of(String title, String contents) {
        BoardEntity board = new BoardEntity();
        board.title = title;
        board.contents = contents;
        return board;
    }

    public BoardEntity(String title, String contents, Long userId) {
        this.title = title;
        this.contents = contents;
        this.user = UserEntity.of(userId);
    }
}

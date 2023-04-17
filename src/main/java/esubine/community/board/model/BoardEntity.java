package esubine.community.board.model;

import esubine.community.category.model.CategoryEntity;
import esubine.community.hashtag.model.BoardHashTagEntity;
import esubine.community.hashtag.model.HashTagEntity;
import esubine.community.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @JoinColumn(name="category_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private CategoryEntity category;

    @JoinColumn(name= "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name="like_count")
    private int likeCount;

    @Column(name="report_count")
    private int reportCount;

    @Column(name="is_delete")
    private boolean isDelete;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BoardHashTagEntity> boardHashTags = new ArrayList<>();

    public static BoardEntity of(String title, String contents) {
        BoardEntity board = new BoardEntity();
        board.title = title;
        board.contents = contents;
        return board;
    }

    public BoardEntity(String title, String contents, Long userId, Long categoryId) {
        this.title = title;
        this.contents = contents;
        this.user = UserEntity.of(userId);
        setBoardCategoryId(categoryId);
    }

    public void setBoardCategoryId(Long categoryId){
        this.category = new CategoryEntity();
        this.category.setCategoryId(categoryId);
    }

    public void addHashTag(HashTagEntity hashTag){
        BoardHashTagEntity boardHashTag = new BoardHashTagEntity(this, hashTag);
        this.boardHashTags.add(boardHashTag);
    }

    public void addHashTags(Collection<HashTagEntity> hashTags){
        hashTags.forEach(this::addHashTag);
    }

    public List<String> getHashTagNames(){
        return boardHashTags.stream()
//                .map(BoardHashTagEntity::getHashtag)
//                .map(HashTagEntity::getName)
                ///////
//                .map((e)->e.getHashtag())
//                .map((e)->e.getName())
                .map((e)->e.getHashtag().getName())
                .toList();
    }

    public void clearHashTag(){
        boardHashTags.clear();
    }
}

package esubine.community.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import esubine.community.board.model.BoardEntity;
import esubine.community.category.dto.CategoryResponse;
import esubine.community.user.dto.UserResponse;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponse {
    private final Long boardId;
    private final String title;
    private final String contents;
//    private final UserResponse user;
    private final CategoryResponse category;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<String> hashtags;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public BoardResponse(BoardEntity board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.contents = board.getContents();
//        this.user = new UserResponse(board.getUser());
        this.category = new CategoryResponse(board.getCategory());
        this.hashtags = board.getHashTagNames();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }

}

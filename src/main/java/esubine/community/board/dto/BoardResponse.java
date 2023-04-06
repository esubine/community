package esubine.community.board.dto;

import esubine.community.board.model.BoardEntity;
import esubine.community.user.dto.UserResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private final Long boardId;
    private final String title;
    private final String contents;
    private final UserResponse user;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public BoardResponse(BoardEntity board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.user = new UserResponse(board.getUser());
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }

}

package esubine.community.board.dto;

import esubine.community.board.model.BoardEntity;
import lombok.Getter;

@Getter
public class BoardCreatedResponse {
    private final Long boardId;

    public BoardCreatedResponse(BoardEntity board) {
        this.boardId = board.getBoardId();
    }
}

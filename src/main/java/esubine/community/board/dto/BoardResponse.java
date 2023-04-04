package esubine.community.board.dto;

import esubine.community.board.model.BoardEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardResponse {
    private final Long boardId;

    public BoardResponse(BoardEntity board) {
        this.boardId = board.getBoardId();
    }

}

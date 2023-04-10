package esubine.community.board.dto;

import esubine.community.board.model.BoardEntity;
import lombok.Getter;

@Getter
public class BoardLikeResponse {
    private final int likeCount;

    public BoardLikeResponse(BoardEntity board){
        this.likeCount = board.getLikeCount();
    }
}


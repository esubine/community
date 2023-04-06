package esubine.community.board.dto;

import esubine.community.board.model.BoardEntity;
import lombok.Getter;

@Getter
public class BoardLikesResponse {
    private final int likes;

    public BoardLikesResponse(BoardEntity board){
        this.likes = board.getLikes();
    }
}

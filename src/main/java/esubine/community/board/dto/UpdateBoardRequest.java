package esubine.community.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBoardRequest {
    private String title;
    private String contents;

}

package esubine.community.board.dto;
import esubine.community.board.model.BoardEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CreateBoardRequest {
    private String title;
    private String contents;
    private List<String> hashtag = new ArrayList<>();

    public BoardEntity toEntity() {
        return BoardEntity.of(
                this.title,
                this.contents
        );
    }
}

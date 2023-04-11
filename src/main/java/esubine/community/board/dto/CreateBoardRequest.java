package esubine.community.board.dto;
import esubine.community.board.model.BoardEntity;
import lombok.Getter;

@Getter
public class CreateBoardRequest {
    private String title;
    private String contents;

    public BoardEntity toEntity() {
        return BoardEntity.of(
                this.title,
                this.contents
        );
    }
}

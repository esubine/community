package esubine.community.board.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateBoardRequest {
    private String title;
    private String contents;
    private Long categoryId;

    // null이면 해시태그 수정이 없는거, empty list 이면 해시태그를 다 지운거
    @Nullable
    private List<String> hashtags;

}

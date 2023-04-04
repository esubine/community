package esubine.community.board;

import esubine.community.auth.AuthInfo;
import esubine.community.board.dto.BoardResponse;
import esubine.community.board.dto.CreateBoardRequest;
import esubine.community.board.model.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {
    private final BoardService boardService;

    //TODO: 게시물 작성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponse createBoard(
            AuthInfo authInfo,
            @RequestBody CreateBoardRequest createBoardRequest
    ) {
        System.out.println("authInfo = " + authInfo.getUserId());
        BoardEntity board = boardService.createBoard(authInfo.getUserId(), createBoardRequest);
        return new BoardResponse(board);
    }

    //TODO: 전체 게시물 검색
    //TODO: 사용자 게시물 검색
    //TODO: 카테고리별 게시물 검색
    //TODO: 게시물 수정(제목, 내용, 카테고리)
    //TODO: 게시물 삭제
    //TODO: 게시물 좋아요 기능
    //TODO: 게시물 신고
}




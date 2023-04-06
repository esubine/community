package esubine.community.board;

import esubine.community.EmptyResponse;
import esubine.community.auth.AuthInfo;
import esubine.community.board.dto.BoardResponse;
import esubine.community.board.dto.CreateBoardRequest;
import esubine.community.board.dto.UpdateBoardRequest;
import esubine.community.board.model.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponse createBoard(
            AuthInfo authInfo,
            @RequestBody CreateBoardRequest createBoardRequest
    ) {
        BoardEntity board = boardService.createBoard(authInfo.getUserId(), createBoardRequest);
        return new BoardResponse(board);
    }

    @GetMapping
    public List<BoardResponse> getTotalBoard() {
        List<BoardEntity> boardList = boardService.getBoard();
        return boardService.responseBoard(boardList);
    }

    @GetMapping("/userId/{userId}")
    public List<BoardResponse> getBoardByUserId(
            @PathVariable("userId") Long userId
    ) {
        List<BoardEntity> boardList = boardService.getBoardByUserId(userId);
        return boardService.responseBoard(boardList);

    }

    //TODO: 카테고리별 게시물 검색


    @PatchMapping("/{boardId}")
    public BoardResponse updateBoard(
            AuthInfo authInfo,
            @PathVariable("boardId") Long boardId,
            @RequestBody UpdateBoardRequest updateBoardRequest
    ) {
        BoardEntity board = boardService.updateBoard(authInfo.getUserId(), boardId, updateBoardRequest);
        return new BoardResponse(board);
    }

    @DeleteMapping("/{boardId}")
    public EmptyResponse deleteBoard(
        AuthInfo authInfo,
        @PathVariable("boardId") Long boardId
    ){
        boardService.deleteBoard(authInfo.getUserId(), boardId);
        return new EmptyResponse();
    }

    //TODO: 게시물 좋아요 기능
    @PostMapping("/{boardId}/like")
    public int likeBoard(
            AuthInfo authInfo,
            @PathVariable("boardId") Long boardId,
            @RequestBody LikeRequest likeRequest
    ) {
        return boardService.likeBoard(authInfo.getUserId(), boardId, likeRequest);
    }

    //TODO: 게시물 신고

}




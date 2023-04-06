package esubine.community.board;

import esubine.community.EmptyResponse;
import esubine.community.auth.AuthInfo;
import esubine.community.board.dto.*;
import esubine.community.board.model.BoardEntity;
import jakarta.transaction.Transactional;
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
    public BoardCreatedResponse createBoard(
            AuthInfo authInfo,
            @RequestBody CreateBoardRequest createBoardRequest
    ) {
        BoardEntity board = boardService.createBoard(authInfo.getUserId(), createBoardRequest);
        return new BoardCreatedResponse(board);
    }

    @GetMapping("/{boardId}")
    public BoardResponse getBoardByBoardId(
            @PathVariable("boardId") Long boardId
    ) {
        BoardEntity board = boardService.getBoardById(boardId);
        BoardResponse boardResponse = new BoardResponse(board);
        return boardResponse;
    }

    @GetMapping
    public List<BoardResponse> getBoardByUserId(
            @RequestParam(value = "userId", required = false) Long userId
    ) {
        if (userId == null) {
            List<BoardEntity> boardList = boardService.getBoard();
            return boardService.responseBoard(boardList);
        } else {
            List<BoardEntity> boardList = boardService.getBoardByUserId(userId);
            return boardService.responseBoard(boardList);
        }
    }

    //TODO: 카테고리별 게시물 검색


    @PatchMapping("/{boardId}")
    public EmptyResponse updateBoard(
            AuthInfo authInfo,
            @PathVariable("boardId") Long boardId,
            @RequestBody UpdateBoardRequest updateBoardRequest
    ) {
        BoardEntity board = boardService.updateBoard(authInfo.getUserId(), boardId, updateBoardRequest);
        return new EmptyResponse();
    }

    @DeleteMapping("/{boardId}")
    public EmptyResponse deleteBoard(
            AuthInfo authInfo,
            @PathVariable("boardId") Long boardId
    ) {
        boardService.deleteBoard(authInfo.getUserId(), boardId);
        return new EmptyResponse();
    }

    //TODO: 게시물 좋아요 기능
    @PostMapping("/{boardId}/like")
    public BoardLikesResponse likeBoard(
            AuthInfo authInfo,
            @PathVariable("boardId") Long boardId,
            @RequestBody LikeRequest likeRequest
    ) {
        return boardService.likeBoard(authInfo.getUserId(), boardId, likeRequest);
    }

    //TODO: 게시물 신고

}




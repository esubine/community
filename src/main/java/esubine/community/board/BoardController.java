package esubine.community.board;

import esubine.community.EmptyResponse;
import esubine.community.auth.AuthInfo;
import esubine.community.board.dto.*;
import esubine.community.board.model.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
            @RequestBody CreateBoardRequest createBoardRequest,
            @RequestParam(value = "categoryId") Long categoryId
    ) {
        BoardEntity board = boardService.createBoard(authInfo.getUserId(), createBoardRequest, categoryId);
        return new BoardCreatedResponse(board);
    }

    @GetMapping("/{boardId}")
    public BoardResponse getBoardByBoardId(
            @PathVariable("boardId") Long boardId,
            AuthInfo authInfo
    ) {
        BoardEntity board = boardService.getBoardById(boardId, authInfo.getUserId());
        BoardResponse boardResponse = new BoardResponse(board);
        return boardResponse;
    }

    @GetMapping
    public List<BoardResponse> getBoardByUserId(
            @RequestParam(value = "userId", required = false) Long userId,
            @PageableDefault(page = 0, size = 5)
            Pageable pageable,
            AuthInfo authInfo
    ) {
        if (userId == null) {
            List<BoardEntity> boardList = boardService.getBoard(pageable, authInfo.getUserId());
            return boardService.responseBoard(boardList);
        } else {
            List<BoardEntity> boardList = boardService.getBoardByUserId(pageable, userId, authInfo.getUserId());
            return boardService.responseBoard(boardList);
        }
    }

    //TODO: 카테고리별 게시물 검색
    @GetMapping("/category")
    public List<BoardResponse> getByCategoryId(
            @RequestParam("id") Long categoryId,
            AuthInfo authInfo,
            @PageableDefault(page = 0, size = 5)
            Pageable pageable
    ) {
        List<BoardEntity> boardEntityList = boardService.getByCategoryId(categoryId, pageable, authInfo.getUserId());
        return boardService.responseBoard(boardEntityList);
    }


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

    @PostMapping("/{boardId}/like")
    public EmptyResponse likeBoard(
            AuthInfo authInfo,
            @PathVariable("boardId") Long boardId,
            @RequestBody LikeRequest likeRequest
    ) {
        return boardService.likeBoard(authInfo.getUserId(), boardId, likeRequest);
    }

    @PostMapping("{boardId}/report")
    public EmptyResponse reportBoard(
            AuthInfo authInfo,
            @PathVariable("boardId") Long boardId,
            @RequestBody ReportRequest reportRequest
    ) {
        boardService.reportBoard(authInfo.getUserId(), boardId, reportRequest);
        return new EmptyResponse();
    }
}




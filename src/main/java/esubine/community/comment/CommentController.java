package esubine.community.comment;

import esubine.community.EmptyResponse;
import esubine.community.auth.AuthInfo;
import esubine.community.comment.dto.CommentRequest;
import esubine.community.comment.dto.CommentResponse;
import esubine.community.comment.model.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EmptyResponse createComment(
            AuthInfo authInfo,
            @RequestParam(value = "boardId") Long boardId,
            @RequestParam(value = "commentId") Long commentId,
            @RequestBody CommentRequest commentRequest
    ) {
        return commentService.createComment(authInfo.getUserId(), boardId, commentId, commentRequest);
    }


    @PatchMapping("/{commentId}")
    public EmptyResponse updateComment(
            AuthInfo authInfo,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentRequest commentRequest
    ) {
        return commentService.updateComment(authInfo.getUserId(), commentId, commentRequest);
    }

    @DeleteMapping("{commentId}")
    public EmptyResponse deleteComment(
            AuthInfo authInfo,
            @PathVariable("commentId") Long commentId
    ) {
        return commentService.deleteComment(authInfo.getUserId(), commentId);
    }

    @GetMapping
    public List<CommentResponse> getCommentByBoardId(
            @RequestParam(value = "boardId") Long boardId,
            @PageableDefault(page = 0, size = 3)
            Pageable pageable
    ) {
        List<CommentEntity> commentEntityList = commentService.getCommentByBoardId(boardId, pageable);
        return commentService.responseBoard(commentEntityList);
    }

    @GetMapping("/user")
    public List<CommentResponse> getCommentByUserId(
            AuthInfo authInfo,
            @PageableDefault(page = 0, size = 5)
            Pageable pageable
    ) {
        List<CommentEntity> commentEntityList = commentService.getCommentByUserId(authInfo.getUserId(), pageable);
        return commentService.responseBoard(commentEntityList);
    }

}
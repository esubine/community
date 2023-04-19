package esubine.community.comment;

import esubine.community.EmptyResponse;
import esubine.community.auth.AuthInfo;
import esubine.community.comment.dto.ChildCommentResponse;
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
            @RequestParam(value = "boardId", required = false) Long boardId,
            @RequestParam(value = "parentCommentId", required = false) Long parentCommentId,
            @RequestBody CommentRequest commentRequest
    ) {
        commentService.createComment(authInfo.getUserId(), boardId, parentCommentId, commentRequest);
        return new EmptyResponse();
    }


    @PatchMapping("/{commentId}")
    public EmptyResponse updateComment(
            AuthInfo authInfo,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentRequest commentRequest
    ) {
        commentService.updateComment(authInfo.getUserId(), commentId, commentRequest);
        return new EmptyResponse();
    }

    @DeleteMapping("{commentId}")
    public EmptyResponse deleteComment(
            AuthInfo authInfo,
            @PathVariable("commentId") Long commentId
    ) {
        commentService.deleteComment(authInfo.getUserId(), commentId);
        return new EmptyResponse();
    }

    @GetMapping
    public List<CommentResponse> getCommentByBoardId(
            @RequestParam(value = "boardId") Long boardId,
            @PageableDefault(page = 0, size = 3)
            Pageable pageable
    ) {
        List<CommentEntity> commentEntityList = commentService.getCommentByBoardId(boardId, pageable);
        return commentService.response(commentEntityList);
    }

    @GetMapping("/user")
    public List<ChildCommentResponse> getCommentByUserId(
            AuthInfo authInfo,
            @PageableDefault(page = 0, size = 5)
            Pageable pageable
    ) {
        List<CommentEntity> commentEntityList = commentService.getCommentByUserId(authInfo.getUserId(), pageable);
        return commentEntityList.stream()
                .map(ChildCommentResponse::new)
                .toList();
    }

}
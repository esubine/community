package esubine.community.comment;

import esubine.community.EmptyResponse;
import esubine.community.auth.AuthInfo;
import esubine.community.comment.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody CommentRequest commentRequest
    ) {
        commentService.createComment(authInfo.getUserId(), boardId, commentRequest);
        return new EmptyResponse();
    }

    @PatchMapping("/{commentId}")
    public EmptyResponse updateComment(
            AuthInfo authInfo,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentRequest commentRequest
    ){
        commentService.updateComment(authInfo.getUserId(),commentId, commentRequest);
        return new EmptyResponse();
    }


}

package esubine.community.comment;

import esubine.community.EmptyResponse;
import esubine.community.auth.AuthInfo;
import esubine.community.comment.dto.CreateCommentRequest;
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
            @RequestBody CreateCommentRequest createCommentRequest
    ) {
        commentService.createComment(authInfo.getUserId(), boardId, createCommentRequest);
        return new EmptyResponse();
    }


}

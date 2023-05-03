package esubine.community.comment.dto;

import esubine.community.comment.model.CommentEntity;
import esubine.community.user.dto.UserResponse;
import lombok.Getter;

@Getter
public class ChildCommentResponse {
    private final Long commentId;
    private final String comment;
    private final Long boardId;
    private final UserResponse user;

    public ChildCommentResponse(CommentEntity comment) {
        this.commentId = comment.getCommentId();
        if (comment.isDelete()) {
            this.comment = "삭제된 댓글입니다.";
            this.user = null;
        } else {
            this.comment = comment.getComment();
            this.user = new UserResponse(comment.getUser());
        }
        this.boardId = comment.getBoardId();
    }
}

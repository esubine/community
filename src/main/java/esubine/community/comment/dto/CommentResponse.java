package esubine.community.comment.dto;

import esubine.community.comment.model.CommentEntity;
import esubine.community.user.dto.UserResponse;
import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long commentId;
    private final String comment;
    private final Long boardId;
    private final UserResponse user;


    public CommentResponse(CommentEntity comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.boardId = comment.getBoardId();
        this.user = new UserResponse(comment.getUser());

    }
}
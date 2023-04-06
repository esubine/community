package esubine.community.comment.dto;

import esubine.community.comment.model.CommentEntity;

public class CommentCreatedResponse {
    private final Long commentId;

    public CommentCreatedResponse(CommentEntity comment) {
        this.commentId = comment.getCommentId();
    }
}
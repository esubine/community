package esubine.community.comment;

import esubine.community.comment.dto.CreateCommentRequest;
import esubine.community.comment.model.CommentEntity;
import esubine.community.comment.model.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public CommentEntity createComment(Long userId, Long boardId, CreateCommentRequest createCommentRequest) {
        CommentEntity comment = new CommentEntity(userId, boardId, createCommentRequest.getComment());
        return commentRepository.save(comment);
    }
}

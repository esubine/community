package esubine.community.comment;

import esubine.community.comment.dto.CommentRequest;
import esubine.community.comment.model.CommentEntity;
import esubine.community.comment.model.CommentRepository;
import esubine.community.exception.AuthException;
import esubine.community.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentEntity createComment(Long userId, Long boardId, CommentRequest createCommentRequest) {
        CommentEntity comment = new CommentEntity(userId, boardId, createCommentRequest.getComment());
        return commentRepository.save(comment);
    }

    public CommentEntity updateComment(Long userId, Long commentId, CommentRequest commentRequest) {
        Optional<CommentEntity> commentOptional = commentRepository.findById(commentId);
        CommentEntity comment = commentOptional.orElseThrow(() -> new NoDataException("댓글을 찾을 수 없습니다."));

        if(userId.equals(comment.getUserId())){
            comment.setComment(commentRequest.getComment());
            commentRepository.save(comment);
        }
        else{
            throw new AuthException("작성자만 수정할 수 있습니다.");
        }
        return comment;
    }

}

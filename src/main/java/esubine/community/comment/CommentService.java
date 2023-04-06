package esubine.community.comment;

import esubine.community.EmptyResponse;
import esubine.community.comment.dto.CommentRequest;
import esubine.community.comment.dto.CommentResponse;
import esubine.community.comment.model.CommentEntity;
import esubine.community.comment.model.CommentRepository;
import esubine.community.exception.AuthException;
import esubine.community.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public EmptyResponse createComment(Long userId, Long boardId, CommentRequest createCommentRequest) {
        CommentEntity comment = new CommentEntity(userId, boardId, createCommentRequest.getComment());
        commentRepository.save(comment);
        return new EmptyResponse();
    }

    public EmptyResponse updateComment(Long userId, Long commentId, CommentRequest commentRequest) {
        Optional<CommentEntity> commentOptional = commentRepository.findById(commentId);
        CommentEntity comment = commentOptional.orElseThrow(() -> new NoDataException("댓글을 찾을 수 없습니다."));

        if (userId.equals(comment.getUser().getId())) {
            comment.setComment(commentRequest.getComment());
            commentRepository.save(comment);
        } else {
            throw new AuthException("작성자만 수정할 수 있습니다.");
        }
        return new EmptyResponse();
//        return comment;
    }

    public EmptyResponse deleteComment(Long userId, Long commentId) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);
        CommentEntity comment = commentEntityOptional.orElseThrow(() -> new NoDataException("댓글을 찾을 수 없습니다."));

        if (userId.equals(comment.getUser().getId())) {
            commentRepository.deleteById(commentId);
        } else {
            throw new AuthException("작성자만 삭제할 수 있습니다.");
        }
        return new EmptyResponse();
    }

    public List<CommentEntity> getCommentByBoardId(Long boardId) {
        List<CommentEntity> commentEntityList = commentRepository.getAllByBoardId(boardId);
        if (commentEntityList.isEmpty()) {
            throw new NoDataException("작성된 댓글이 없습니다.");
        }
        return commentEntityList;
    }

    public List<CommentResponse> responseBoard(List<CommentEntity> commentEntityList) {
        List<CommentResponse> result = new ArrayList<>();
        for (int i = 0; i < commentEntityList.size(); i++) {
            CommentEntity comment = commentEntityList.get(i);
            CommentResponse commentResponse = new CommentResponse(comment);
            result.add(commentResponse);
        }
        return result;
    }

    public List<CommentEntity> getCommentByUserId(Long userId) {
        List<CommentEntity> commentEntityList = commentRepository.getAllByUserId(userId);
        if (commentEntityList.isEmpty()) {
            throw new NoDataException("작성한 댓글이 없습니다.");
        }
        return commentEntityList;
    }
}

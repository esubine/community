package esubine.community.comment;

import esubine.community.EmptyResponse;
import esubine.community.comment.dto.CommentRequest;
import esubine.community.comment.dto.CommentResponse;
import esubine.community.comment.model.CommentEntity;
import esubine.community.comment.model.CommentRepository;
import esubine.community.exception.AuthException;
import esubine.community.exception.MisMatchException;
import esubine.community.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public EmptyResponse createComment(Long userId, Long boardId, Long parentCommentId, CommentRequest createCommentRequest) {

        if (parentCommentId != null) {
            Optional<CommentEntity> commentEntity = commentRepository.findById(parentCommentId);
            commentEntity.orElseThrow(() -> new NoDataException("없는 댓글 입니다."));
            if (commentEntity.get().getParentCommentId() != null) {
                throw new MisMatchException("root 댓글에만 대댓글을 작성할 수 있습니다.");
            }
        }


        CommentEntity comment = new CommentEntity(userId, boardId, parentCommentId, createCommentRequest.getComment());
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

    public List<CommentEntity> getCommentByBoardId(Long boardId, Pageable pageable) {
        List<CommentEntity> commentEntityList = commentRepository.getCommentAllByBoardId(boardId, pageable);
        if (commentEntityList.isEmpty()) {
            throw new NoDataException("작성된 댓글이 없습니다.");
        }
        return commentEntityList;
    }

    public List<CommentResponse> responseBoard(List<CommentEntity> commentEntityList) {
        List<CommentResponse> result = new ArrayList<>();
        for (int i = 0; i < commentEntityList.size(); i++) {
            CommentEntity comment = commentEntityList.get(i);
            result.add(new CommentResponse(comment));
        }
        return result;
    }

    public List<CommentEntity> getCommentByUserId(Long userId, Pageable pageable) {
        List<CommentEntity> commentEntityList = commentRepository.getCommentByUserId(userId, pageable);
        if (commentEntityList.isEmpty()) {
            throw new NoDataException("작성한 댓글이 없습니다.");
        }
        return commentEntityList;
    }
    //TODO: 댓글 작성하기 수정 - 대댓글 다는 경우에 root 댓글에 대댓글 작성할수있도록하기, parentCommentId가 null이 아닌경우에 댓글 작성하는건 예외처리
}

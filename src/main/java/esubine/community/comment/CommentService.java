package esubine.community.comment;

import esubine.community.board.model.BoardEntity;
import esubine.community.board.model.BoardRepository;
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

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public void createComment(Long userId, Long boardId, Long parentCommentId, CommentRequest createCommentRequest) {

        if (boardId == null && parentCommentId == null) {
            throw new NoDataException("boardId, parentCommentId 둘 중 하나는 꼭 입력해야합니다.");
        }

        if (boardId != null && parentCommentId == null) {
            boardRepository.getByBoardId(boardId).orElseThrow(() -> new NoDataException("해당 게시물이 존재하지 않습니다."));
            CommentEntity comment = new CommentEntity(userId, boardId, null, createCommentRequest.getComment());
            commentRepository.save(comment);
            return;
        }

        CommentEntity parentComment = commentRepository.findByCommentId(parentCommentId).orElseThrow(() -> new NoDataException("댓글을 찾을 수 없습니다."));

        if (parentComment.getParentCommentId() != null) {
            throw new MisMatchException("root 댓글에만 대댓글을 작성할 수 있습니다.");
        }
        if (boardId != null && parentCommentId != null) {
            if (!parentComment.getBoardId().equals(boardId)) throw new MisMatchException("게시물을 찾을 수 없습니다.");
        }

        CommentEntity childComment = new CommentEntity(userId, parentComment.getBoardId(), parentComment.getCommentId(), createCommentRequest.getComment());
        commentRepository.save(childComment);

    }

    public void updateComment(Long userId, Long commentId, CommentRequest commentRequest) {
        CommentEntity comment = commentRepository.getByCommentId(commentId).orElseThrow(() -> new NoDataException("댓글을 찾을 수 없습니다."));

        if (userId.equals(comment.getUser().getId())) {
            comment.setComment(commentRequest.getComment());
            commentRepository.save(comment);
        } else {
            throw new AuthException("작성자만 수정할 수 있습니다.");
        }
    }

    public void deleteComment(Long userId, Long commentId) {
        CommentEntity comment = commentRepository.getByCommentId(commentId).orElseThrow(() -> new NoDataException("댓글을 찾을 수 없습니다."));

        if (userId.equals(comment.getUser().getId())) {
            comment.setDelete(true);
            commentRepository.save(comment);
        } else {
            throw new AuthException("작성자만 삭제할 수 있습니다.");
        }
    }

    public List<CommentEntity> getCommentByBoardId(Long boardId, Pageable pageable) {
        BoardEntity board = boardRepository.getByBoardId(boardId).orElseThrow(() -> new NoDataException("해당 게시물이 존재하지 않습니다."));

        List<CommentEntity> commentEntityList = commentRepository.getCommentAllByBoardId(boardId, pageable);
        if (commentEntityList.isEmpty()) {
            throw new NoDataException("작성된 댓글이 없습니다.");
        }
        return commentEntityList;
    }

    public List<CommentResponse> response(List<CommentEntity> commentEntityList) {
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
}
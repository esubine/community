package esubine.community.board;

import esubine.community.EmptyResponse;
import esubine.community.board.dto.*;
import esubine.community.board.model.*;
import esubine.community.category.model.CategoryEntity;
import esubine.community.category.model.CategoryRepository;
import esubine.community.exception.AuthException;
import esubine.community.exception.DuplicatedException;
import esubine.community.exception.MisMatchException;
import esubine.community.exception.NoDataException;
import esubine.community.hashtag.HashTagService;
import esubine.community.hashtag.model.HashTagEntity;
import esubine.community.user.model.BlockUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final LikeInfoRepository likeInfoRepository;
    private final BoardReportInfoRepository boardReportInfoRepository;
    private final BlockUserRepository blockUserRepository;
    private final CategoryRepository categoryRepository;

    private final HashTagService hashTagService;

    public BoardEntity createBoard(Long userId, CreateBoardRequest createBoardRequest, Long categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) throw new NoDataException("해당 카테고리가 존재하지 않습니다.");

        BoardEntity board = new BoardEntity(createBoardRequest.getTitle(), createBoardRequest.getContents(), userId, categoryId);
        List<HashTagEntity> hashTagEntityList = hashTagService.convertHashTagEntity(createBoardRequest.getHashtag());
        board.addHashTags(hashTagEntityList);

        return boardRepository.save(board);
    }


    public List<BoardResponse> responseBoard(List<BoardEntity> boardList) {
        return boardList.stream().map(BoardResponse::new).toList();

//        List<BoardResponse> result = new ArrayList<>();
//        for (int i = 0; i < boardList.size(); i++) {
//            BoardEntity board = boardList.get(i);
//            BoardResponse boardResponse = new BoardResponse(board);
//            result.add(boardResponse);
//        }
//        return result;
    }

    public List<BoardEntity> getBoard(Pageable pageable, Long userId) {
        return boardRepository.getAll(pageable, userId);
    }

    public List<BoardEntity> getBoardByUserId(Pageable pageable, Long userId, Long requesterId) {

        if (blockUserRepository.findByRequesterIdAndTargetId(requesterId, userId).isPresent()) {
            throw new AuthException("차단한 유저입니다.");
        } else {
//            List<BoardEntity> boards = boardRepository.getByUserId(pageable, userId, requesterId);
            return boardRepository.getByUserId(pageable, userId);
        }
    }

    public BoardEntity getBoardById(Long boardId, Long requesterId) {
        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
        if (boardOptional.isEmpty()) throw new NoDataException("해당 게시물이 존재하지 않습니다.");
        BoardEntity board = boardOptional.get();

        if (blockUserRepository.findByRequesterIdAndTargetId(requesterId, board.getUser().getId()).isPresent()) {
            throw new AuthException("차단한 유저의 게시물입니다.");
        }
        if (board.getReportCount() > 5) {
            throw new AuthException("신고된 게시물입니다.");
        } else {
            return boardRepository.getByBoardId(boardId).orElseThrow(() -> new NoDataException("존재하지 않은 게시물 입니다."));
//        return boardRepository.findByBoardId(boardId);
        }
    }

    public List<BoardEntity> getByCategoryId(Long categoryId, Pageable pageable, Long requesterId) {
        return boardRepository.getByCategoryId(categoryId, pageable, requesterId);
    }


    public BoardEntity updateBoard(Long userId, Long boardId, UpdateBoardRequest updateBoardRequest) {

        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
        if (boardOptional.isEmpty()) throw new NoDataException("해당 게시물이 존재하지 않습니다.");
        BoardEntity board = boardOptional.get();

        if (userId.equals(board.getUser().getId())) {
            if (updateBoardRequest.getTitle() != null) {
                board.setTitle(updateBoardRequest.getTitle());
            }

            if (updateBoardRequest.getContents() != null) {
                board.setContents(updateBoardRequest.getContents());
            }
            if (updateBoardRequest.getCategoryId() != null) {
                categoryRepository.findById(updateBoardRequest.getCategoryId()).orElseThrow(() -> new NoDataException("없는 카테고리입니다."));
                board.setBoardCategoryId(updateBoardRequest.getCategoryId());
            }
        } else {
            throw new AuthException("작성자만 수정할 수 있습니다.");
        }
        return boardRepository.save(board);

    }

    public BoardEntity deleteBoard(Long userId, Long boardId) {
//        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
//        if (boardOptional.isEmpty()) throw new NoDataException("해당 게시물이 존재하지 않습니다.");
//        BoardEntity board = boardOptional.get();

        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
        BoardEntity board = boardOptional.orElseThrow(() -> new NoDataException("해당 게시물이 존재하지 않습니다."));

        if (userId.equals(board.getUser().getId())) {
            boardRepository.deleteById(boardId);
        } else {
            throw new AuthException("작성자만 삭제할 수 있습니다.");
        }
        return board;
    }

    @Transactional
    public EmptyResponse likeBoard(Long userId, Long boardId, LikeRequest likeRequest) {
        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
        boardOptional.orElseThrow(() -> new NoDataException("해당 게시물이 존재하지 않습니다."));

        LikeInfoEntity likesInfo = likeInfoRepository.findByBoardIdAndUserId(boardId, userId);
        if (likesInfo == null && !likeRequest.isLike()) throw new MisMatchException("좋아요를 안눌렀는데 어떻게 좋아요 취소하나요.");

        if (likeRequest.isLike()) {
            if (likesInfo != null) {
                throw new DuplicatedException("이미 좋아요를 누른 게시물입니다.");
            } else {
                LikeInfoEntity likeInfo = new LikeInfoEntity(userId, boardId);
                boardRepository.increaseLikeCount(boardId);
                try {
                    likeInfoRepository.save(likeInfo);
                } catch (DataIntegrityViolationException e) {
                    throw new DuplicatedException("이미 좋아요를 누른 게시물입니다.");
                }
            }
        } else {
            boardRepository.decreaseLikeCount(boardId);
            int affectedRow = likeInfoRepository.deleteAndGetResult(likesInfo);
            if (affectedRow == 0) throw new DuplicatedException("좋아요를 안눌렀는데 어떻게 좋아요 취소하나요.");
        }
        return new EmptyResponse();
    }

    //TODO: 신고 누적 수에 따라 게시물 처리
    public EmptyResponse reportBoard(Long userId, Long boardId, ReportRequest reportRequest) {
        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
        BoardEntity board = boardOptional.orElseThrow(() -> new NoDataException("해당 게시물이 존재하지 않습니다."));

        int count = board.getReportCount();
        BoardReportInfoEntity boardReportInfoEntity = new BoardReportInfoEntity();

        if (reportRequest.isReport()) {
            if (!boardReportInfoRepository.findAllByBoardIdAndUserId(boardId, userId).isEmpty()) {
                throw new DuplicatedException("이미 신고한 게시물입니다.");
            } else {
                count++;
                board.setReportCount(count);
                boardReportInfoEntity.setUserId(userId);
                boardReportInfoEntity.setBoardId(boardId);
                boardRepository.save(board);
                boardReportInfoRepository.save(boardReportInfoEntity);
            }
        } else {
            if (!boardReportInfoRepository.findAllByBoardIdAndUserId(boardId, userId).isEmpty()) {
                count--;
                if (board.getReportCount() <= 0) {
                    board.setReportCount(0);
                } else {
                    board.setReportCount(count);
                }
                BoardReportInfoEntity BoardReportInfoEntity = boardReportInfoRepository.findByBoardIdAndUserId(boardId, userId);
                boardRepository.save(board);
                boardReportInfoRepository.delete(BoardReportInfoEntity);
            }
        }
        return new EmptyResponse();
    }

}








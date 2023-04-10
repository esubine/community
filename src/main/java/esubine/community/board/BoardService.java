package esubine.community.board;

import esubine.community.EmptyResponse;
import esubine.community.board.dto.*;
import esubine.community.board.model.*;
import esubine.community.exception.AuthException;
import esubine.community.exception.DuplicatedException;
import esubine.community.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final LikesInfoRepository likesInfoRepository;
    private final BoardReportInfoRepository boardReportInfoRepository;

    public BoardEntity createBoard(Long userId, CreateBoardRequest createBoardRequest) {
        BoardEntity board = new BoardEntity(createBoardRequest.getTitle(), createBoardRequest.getContents(), userId);
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

    public List<BoardEntity> getBoard(Pageable pageable) {
        return boardRepository.getAll(pageable);
    }

    public List<BoardEntity> getBoardByUserId(Pageable pageable, Long userId) {
        List<BoardEntity> boards = boardRepository.getByUserId(pageable, userId);




        return boardRepository.getByUserId(pageable, userId);
    }

    public BoardEntity getBoardById(Long boardId) {
        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
        if (boardOptional.isEmpty()) throw new NoDataException("해당 게시물이 존재하지 않습니다.");
        BoardEntity board = boardOptional.get();
        if (board.getReports() > 5) {
            throw new AuthException("신고된 게시물입니다.");
        } else {
            return boardRepository.getByBoardId(boardId).orElseThrow(() -> new NoDataException("존재하지 않은 게시물 입니다."));
//        return boardRepository.findByBoardId(boardId);
        }
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

    public BoardLikesResponse likeBoard(Long userId, Long boardId, LikeRequest likeRequest) {
        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
        BoardEntity board = boardOptional.orElseThrow(() -> new NoDataException("해당 게시물이 존재하지 않습니다."));

        return likeAdd(board, userId, boardId, likeRequest);

    }

    public BoardLikesResponse likeAdd(BoardEntity board, Long userId, Long boardId, LikeRequest likeRequest) {
        int count = board.getLikes();
        LikesInfoEntity likesInfo = new LikesInfoEntity();

        if (likeRequest.isLike()) {
            if (!likesInfoRepository.findAllByBoardIdAndUserId(boardId, userId).isEmpty()) {
                throw new DuplicatedException("이미 좋아요를 누른 게시물입니다.");
            } else {
                count++;
                board.setLikes(count);
                likesInfo.setUserId(userId);
                likesInfo.setBoardId(boardId);
                boardRepository.save(board);
                likesInfoRepository.save(likesInfo);
            }
        } else {
            if (!likesInfoRepository.findAllByBoardIdAndUserId(boardId, userId).isEmpty()) {
                count--;
                if (board.getLikes() <= 0) {
                    board.setLikes(0);
                } else {
                    board.setLikes(count);
                }
                LikesInfoEntity likesInfoEntity = likesInfoRepository.findByBoardIdAndUserId(boardId, userId);
                boardRepository.save(board);
                likesInfoRepository.delete(likesInfoEntity);
            }
        }
        return new BoardLikesResponse(board);
    }

    //TODO: 신고 누적 수에 따라 게시물 처리
    public EmptyResponse reportBoard(Long userId, Long boardId, ReportRequest reportRequest) {
        Optional<BoardEntity> boardOptional = boardRepository.getByBoardId(boardId);
        BoardEntity board = boardOptional.orElseThrow(() -> new NoDataException("해당 게시물이 존재하지 않습니다."));

        int count = board.getReports();
        BoardReportInfoEntity boardReportInfoEntity = new BoardReportInfoEntity();

        if (reportRequest.isReport()) {
            if (!boardReportInfoRepository.findAllByBoardIdAndUserId(boardId, userId).isEmpty()) {
                throw new DuplicatedException("이미 신고한 게시물입니다.");
            } else {
                count++;
                board.setReports(count);
                boardReportInfoEntity.setUserId(userId);
                boardReportInfoEntity.setBoardId(boardId);
                boardRepository.save(board);
                boardReportInfoRepository.save(boardReportInfoEntity);
            }
        } else {
            if (!boardReportInfoRepository.findAllByBoardIdAndUserId(boardId, userId).isEmpty()) {
                count--;
                if (board.getReports() <= 0) {
                    board.setReports(0);
                } else {
                    board.setReports(count);
                }
                BoardReportInfoEntity BoardReportInfoEntity = boardReportInfoRepository.findByBoardIdAndUserId(boardId, userId);
                boardRepository.save(board);
                boardReportInfoRepository.delete(BoardReportInfoEntity);
            }
        }
        return new EmptyResponse();
    }
}








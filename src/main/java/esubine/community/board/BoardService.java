package esubine.community.board;

import esubine.community.board.dto.BoardResponse;
import esubine.community.board.dto.CreateBoardRequest;
import esubine.community.board.dto.UpdateBoardRequest;
import esubine.community.board.model.BoardEntity;
import esubine.community.board.model.BoardRepository;
import esubine.community.exception.AuthException;
import esubine.community.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardEntity createBoard(Long userId, CreateBoardRequest createBoardRequest) {
        //userId 강제입력
        userId = 7l;

        BoardEntity board = new BoardEntity(createBoardRequest.getTitle(), createBoardRequest.getContents(), userId);
        return boardRepository.save(board);
    }

    public List<BoardResponse> responseBoard(List<BoardEntity> boardList) {
        List<BoardResponse> result = new ArrayList<>();
        for (int i = 0; i < boardList.size(); i++) {
            BoardEntity board = boardList.get(i);
            BoardResponse boardResponse = new BoardResponse(
                    board.getBoardId(),
                    board.getTitle(),
                    board.getContents(),
                    board.getUserId(),
                    board.getCreatedAt(),
                    board.getUpdatedAt()
            );
            result.add(boardResponse);
        }
        return result;
    }


    public List<BoardEntity> getBoard() {
        return boardRepository.findAll();
    }

    public List<BoardEntity> getBoardByUserId(Long userId) {
        return boardRepository.findByUserId(userId);
    }

    public Optional<BoardEntity> getBoardById(Long boardId) {
        return boardRepository.findById(boardId);
    }

    public BoardEntity updateBoard(Long userId, Long boardId, UpdateBoardRequest updateBoardRequest) {
        // userId 강제입력
        userId = 7l;
        Optional<BoardEntity> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isEmpty()) throw new NoDataException("해당 게시물이 존재하지 않습니다.");
        BoardEntity board = boardOptional.get();
        System.out.println("updateBoardRequest = " + updateBoardRequest.getTitle());
        if (updateBoardRequest.getTitle() != null) {
            System.out.println("getTitle = " + updateBoardRequest.getTitle());
            board.setTitle(updateBoardRequest.getTitle());
        }
        if (updateBoardRequest.getContents() != null) {
            board.setContents(updateBoardRequest.getContents());
        }
        boardRepository.save(board);
        return board;
    }

    public BoardEntity deleteBoard(Long userId, Long boardId) {
        // userId 강제입력
        userId = 7l;

        Optional<BoardEntity> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isEmpty()) throw new NoDataException("해당 게시물이 존재하지 않습니다.");
        BoardEntity board = boardOptional.get();

        if (userId.equals(board.getUserId())){
            System.out.println(" = " +userId);
            System.out.println("board.getUserId() = " + board.getUserId());
            boardRepository.delete(board);
        }
        else throw new AuthException("작성자만 삭제할 수 있습니다.");
        return board;
    }

}




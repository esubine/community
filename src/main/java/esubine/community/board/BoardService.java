package esubine.community.board;

import esubine.community.board.dto.BoardResponse;
import esubine.community.board.dto.CreateBoardRequest;
import esubine.community.board.model.BoardEntity;
import esubine.community.board.model.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}




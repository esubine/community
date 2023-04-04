package esubine.community.board;

import esubine.community.board.dto.CreateBoardRequest;
import esubine.community.board.model.BoardEntity;
import esubine.community.board.model.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}

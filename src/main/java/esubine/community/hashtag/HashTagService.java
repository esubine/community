package esubine.community.hashtag;

import esubine.community.board.dto.BoardResponse;
import esubine.community.board.model.BoardEntity;
import esubine.community.board.model.BoardRepository;
import esubine.community.exception.NoDataException;
import esubine.community.hashtag.model.BoardHashTagEntity;
import esubine.community.hashtag.model.BoardHashTagRepository;
import esubine.community.hashtag.model.HashTagEntity;
import esubine.community.hashtag.model.HashtagRepository;
import esubine.community.user.model.BlockUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashtagRepository hashtagRepository;
    private final BoardHashTagRepository boardHashTagRepository;

    private final BoardRepository boardRepository;

    private final BlockUserRepository blockUserRepository;

    public List<HashTagEntity> convertHashTagEntity(List<String> names) {
        List<HashTagEntity> originList = hashtagRepository.findAllByNameIn(names);

        List<HashTagEntity> newList = names.stream()
                .filter((name) -> !containsName(originList, name))
                .map((name) -> {
                    HashTagEntity entity = new HashTagEntity(name);
                    return hashtagRepository.save(entity);
                })
                .toList();

        originList.addAll(newList);
        return originList;
    }

    public List<BoardResponse> searchHashtag(String name, Long userId) {
        Long hashtagId = hashtagRepository.getHashtagIdByName(name).orElseThrow(() -> new NoDataException("검색결과가 없습니다."));
        return containBoardId(hashtagId, userId);
    }

    public List<BoardResponse> containBoardId(Long hashtagId, Long userId) {
        List<BoardHashTagEntity> boardHashTagList = boardHashTagRepository.getBoardHashTagEntitiesByHashtagId(hashtagId);
        List<Long> boardIdList = boardHashTagList.stream()
                .map((e) -> e.getBoard().getBoardId())
                .toList();
        return getBoardContents(boardIdList, userId);
    }

    public List<BoardResponse> getBoardContents(List<Long> boardIdList, Long userId) {
        List<BoardEntity> boardList = new ArrayList<>();

        for (int i = 0; i < boardIdList.size(); i++) {
            BoardEntity board = boardRepository.getBoardByBoardId(boardIdList.get(i), userId);
            boardList.add(board);
        }


//
//        for (int i = 0; i < boardIdList.size(); i++) {
//            BoardEntity board = boardRepository.getBoardByBoardId(boardIdList.get(i));
////            if (blockUserRepository.findByRequesterIdAndTargetId(userId, board.getUser().getId()).isPresent()) {
////
////            }
//        }
        return boardList.stream().map(BoardResponse::new).toList();

    }


    private boolean containsName(List<HashTagEntity> list, String name) {
        return list.stream()
                .anyMatch((e) -> e.getName().equals(name));

//                .filter((e) -> e.getName().equals(name))
//                .findAny()
//                .isPresent();
    }

}

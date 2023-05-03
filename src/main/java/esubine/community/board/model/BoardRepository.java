package esubine.community.board.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user " +
            "LEFT JOIN FETCH b.category c "+
            "LEFT JOIN FETCH b.boardHashTags "+
            "WHERE b.user.id=:userId " +
            "AND b.isDelete=false ")
    List<BoardEntity> getByUserId(Pageable pageable, Long userId);


    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user u " +
            "LEFT JOIN FETCH b.category c "+
            "LEFT JOIN FETCH BlockUserEntity bu ON b.user.id = bu.targetId AND bu.requesterId=:userId " +
            "LEFT JOIN FETCH b.boardHashTags "+
            "WHERE bu.blockUserId IS NULL " +
            "AND b.isDelete=false ")
    List<BoardEntity> getAll(Pageable pageable, Long userId);

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user " +
            "LEFT JOIN FETCH BlockUserEntity bu ON b.user.id = bu.targetId "+
            "LEFT JOIN FETCH b.category " +
            "LEFT JOIN FETCH b.boardHashTags "+
            "WHERE b.boardId=:boardId " +
            "AND b.isDelete=false ")
    Optional<BoardEntity> getByBoardId(Long boardId);

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user " +
            "LEFT JOIN FETCH BlockUserEntity bu ON b.user.id = bu.targetId "+
            "LEFT JOIN FETCH b.category " +
            "LEFT JOIN FETCH b.boardHashTags "+
            "WHERE b.boardId=:boardId " +
            "AND b.isDelete=false ")
    BoardEntity getBoardByBoardId(Long boardId);

//    @Query("SELECT b FROM BoardEntity b " +
//            "LEFT JOIN FETCH b.user " +
//            "WHERE b.boardId=:boardId ")
//    Optional<BoardEntity> getByBoardId(Long boardId);

    @Query("SELECT b FROM BoardEntity b " +
            "LEFT JOIN FETCH b.user u " +
            "LEFT JOIN FETCH b.category c " +
            "LEFT JOIN FETCH BlockUserEntity bu ON b.user.id = bu.targetId AND bu.requesterId=:userId " +
            "LEFT JOIN FETCH b.boardHashTags "+
            "WHERE b.category.categoryId=:categoryId " +
            "AND bu.blockUserId IS NULL " +
            "AND b.isDelete=false ")
    List<BoardEntity> getByCategoryId(Long categoryId, Pageable pageable, Long userId);

    @Query("UPDATE BoardEntity b " +
            "SET b.likeCount = b.likeCount+1 " +
            "WHERE b.boardId=:boardId ")
    @Modifying
    void increaseLikeCount(Long boardId);

    @Query("UPDATE BoardEntity b " +
            "SET b.likeCount = b.likeCount-1 " +
            "WHERE b.boardId=:boardId ")
    @Modifying
    void decreaseLikeCount(Long boardId);

    Long countByBoardId(Long boardId);
    Long countByUserId(Long userId);

    @Query("SELECT sum(b.likeCount) from BoardEntity b " +
            "WHERE b.user.id=:userId ")
    Long sumLikeCountByUserId(Long userId);
}

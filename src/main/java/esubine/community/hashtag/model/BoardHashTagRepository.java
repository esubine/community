package esubine.community.hashtag.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardHashTagRepository extends JpaRepository<BoardHashTagEntity, Long> {

    @Query("SELECT bh FROM BoardHashTagEntity bh " +
            "LEFT JOIN FETCH bh.hashtag " +
            "WHERE bh.hashtag.hashTagId=:hashtagId ")
    List<BoardHashTagEntity> getBoardHashTagEntitiesByHashtagId(Long hashtagId);


}

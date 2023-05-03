package esubine.community.hashtag.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<HashTagEntity, Long> {
    List<HashTagEntity> findAllByNameIn(List<String> names);

    @Query("SELECT h.hashTagId FROM HashTagEntity h " +
            "WHERE h.name =:name ")
    Optional<Long> getHashtagIdByName(String name);
}

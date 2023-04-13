package esubine.community.hashtag.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<HashTagEntity, Long> {
    List<HashTagEntity> findAllByNameIn(List<String> names);
}

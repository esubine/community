package esubine.community.hashtag;

import esubine.community.hashtag.model.HashTagEntity;
import esubine.community.hashtag.model.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashtagRepository hashtagRepository;

    public List<HashTagEntity> convertHashTagEntity(List<String> names){
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

    private boolean containsName(List<HashTagEntity> list, String name){
        return list.stream()
                .anyMatch((e) -> e.getName().equals(name));

//                .filter((e) -> e.getName().equals(name))
//                .findAny()
//                .isPresent();
    }

}

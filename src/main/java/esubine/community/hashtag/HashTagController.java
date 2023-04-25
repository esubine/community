package esubine.community.hashtag;

import esubine.community.board.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hashtag")
public class HashTagController {

    private final HashTagService hashTagService;

    @GetMapping
    public List<BoardResponse> searchHashTag(
            @RequestParam(value = "search") String hashtagName
    ) {
       return hashTagService.searchHashtag(hashtagName);
    }
}

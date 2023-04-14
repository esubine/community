package esubine.community.badge;

import esubine.community.auth.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BadgeController {
    private final BadgeService badgeService;

    @GetMapping("/")
    public void test(
            AuthInfo authInfo
    ) {
        badgeService.refreshBadge(authInfo.getUserId());
    }
}

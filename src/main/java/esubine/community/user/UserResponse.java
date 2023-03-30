package esubine.community.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final String realName;
    private final String nickName;
    private final String loginId;
}

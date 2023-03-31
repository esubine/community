package esubine.community.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final String realName;
    private final String nickName;
    private final String loginId;
}

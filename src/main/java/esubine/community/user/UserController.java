package esubine.community.user;

import esubine.community.EmptyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")

public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EmptyResponse createUser(
            @RequestBody CreateUserRequest createUserRequest
    ) {
        userService.createUser(createUserRequest);
        return new EmptyResponse();
    }
    // TODO: 비밀번호 변경

    // TODO: 내 정보 보여주기 (활동 요약) -> 토큰(로그인)정보 확인 후 정보 보여줘야함!!
    @GetMapping("/{id}")
    public UserResponse getUserInfo(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") Long id
    ) {
        UserResponse userResponse = new UserResponse(
                userService.getUserInfo(token, id).get().getRealName(),
                userService.getUserInfo(token, id).get().getNickname(),
                userService.getUserInfo(token, id).get().getLoginId()
        );
        return userResponse;
    }

    // TODO: 닉네임 변경
    // TODO: 탈퇴
}

package esubine.community.user;

import esubine.community.EmptyResponse;
import esubine.community.auth.AuthInfo;
import esubine.community.user.dto.CreateUserRequest;
import esubine.community.user.dto.UpdateNicknameRequest;
import esubine.community.user.dto.UpdatePasswordRequest;
import esubine.community.user.dto.UserResponse;
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
    @PatchMapping("/password")
    public EmptyResponse updatePassword(
//            @RequestHeader("Authorization") String token,
            AuthInfo authInfo,
            @RequestBody UpdatePasswordRequest updatePasswordRequest
    ){
        return userService.updatePassword(authInfo.getUserId(), updatePasswordRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getUserInfo(
            @PathVariable("id") Long id
    ) {
        return userService.getUserInfo(id);

    }

    @PatchMapping
    public UserResponse updateNickname(
            AuthInfo authInfo,
            @RequestBody UpdateNicknameRequest updateNicknameRequest
    ) {
        return userService.updateNickname(authInfo.getUserId(), updateNicknameRequest);
    }

    @DeleteMapping
    public EmptyResponse deleteUser(
            AuthInfo authInfo
    ){
        return userService.deleteUser(authInfo.getUserId());
    }

    //TODO: 비밀번호 찾기

    //TODO: 유저 차단
    @PostMapping("/block/{userId}")
    public EmptyResponse blockUser(
            AuthInfo authInfo,
            @PathVariable("userId") Long targetId
    ){
        userService.blockUser(authInfo.getUserId(), targetId);
        return new EmptyResponse();
    }
}

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
    @PatchMapping("/password")
    public EmptyResponse updatePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdatePasswordRequest updatePasswordRequest
    ){
        return userService.updatePassword(token, updatePasswordRequest);
    }


    @GetMapping("/{id}")
    public UserResponse getUserInfo(
            @PathVariable("id") Long id
    ) {
        return userService.getUserInfo(id);

    }

    @PatchMapping
    public UserResponse updateNickname(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateNicknameRequest updateNicknameRequest
    ) {

        return userService.updateNickname(token, updateNicknameRequest);
    }

    @DeleteMapping
    public EmptyResponse deleteUser(
            @RequestHeader("Authorization") String token
    ){
        return userService.deleteUser(token);
    }

}

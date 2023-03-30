package esubine.community.user;

import esubine.community.exception.DuplicatedException;
import esubine.community.db.user.UserEntity;
import esubine.community.db.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
private final UserRepository userRepository;
    public UserEntity createUser(CreateUserRequest createUserRequest){
        if(userRepository.findByLoginId(createUserRequest.getLoginId())!=null){
            throw new DuplicatedException("아이디 중복");
        }
        if(userRepository.findByNickname(createUserRequest.getNickname())!=null){
            throw new DuplicatedException("닉네임 중복");
        }

        return userRepository.save(createUserRequest.toEntity());
    }


}

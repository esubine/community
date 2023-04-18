package esubine.community.user;

import esubine.community.EmptyResponse;
import esubine.community.auth.model.TokenEntity;
import esubine.community.auth.model.TokenRepository;
import esubine.community.board.model.BoardEntity;
import esubine.community.user.model.BlockUserEntity;
import esubine.community.user.model.BlockUserRepository;
import esubine.community.user.model.UserEntity;
import esubine.community.user.model.UserRepository;
import esubine.community.exception.AuthException;
import esubine.community.exception.DuplicatedException;
import esubine.community.exception.MisMatchException;
import esubine.community.user.dto.CreateUserRequest;
import esubine.community.user.dto.UpdateNicknameRequest;
import esubine.community.user.dto.UpdatePasswordRequest;
import esubine.community.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final BlockUserRepository blockUserRepository;

    public UserEntity createUser(CreateUserRequest createUserRequest) {
        if (userRepository.findByLoginId(createUserRequest.getLoginId()) != null) {
            throw new DuplicatedException("아이디 중복");
        }
        if (userRepository.findByNickname(createUserRequest.getNickname()) != null) {
            throw new DuplicatedException("닉네임 중복");
        }

        return userRepository.save(createUserRequest.toEntity());
    }

    public UserResponse getUserInfo(Long id) {
        TokenEntity token = tokenRepository.findByUserId(id);
        if (token == null || token.isDelete()) throw new AuthException("탈퇴한 회원입니다.");
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new AuthException("존재하지않는 유저입니다."));

        return new UserResponse(user);
    }

    public UserResponse updateNickname(Long userId, UpdateNicknameRequest updateNicknameRequest) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AuthException("존재하지않는 유저입니다."));

        if (userRepository.findByNickname(updateNicknameRequest.getNickname()) == null) {
            user.setNickname(updateNicknameRequest.getNickname());
            userRepository.save(user);
        } else {
            throw new DuplicatedException("중복된 닉네임입니다.");
        }
        return new UserResponse(user);
    }

    @Transactional
    public EmptyResponse deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AuthException("존재하지않는 유저입니다."));

//        TokenEntity token = tokenRepository.findByUserId(userId);
//        if (token.isDelete()) throw new AuthException("탈퇴한 회원입니다.");

        tokenRepository.setAllByUserId(userId);

//        token.setDelete(true);
//        tokenRepository.save(token);

        user.setDelete(true);
        userRepository.save(user);

        return null;
    }

    public EmptyResponse updatePassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AuthException("존재하지 않는 유저입니다."));

        if (user.getLoginPassword().equals(updatePasswordRequest.getPresentPassword())) {
            user.setLoginPassword(updatePasswordRequest.getNewPassword());
            userRepository.save(user);

        } else {
            throw new MisMatchException("기존 비밀번호가 일치하지 않습니다.");
        }
        return new EmptyResponse();
    }

    public EmptyResponse blockUser(Long requesterId, Long targetId) {
        UserEntity requestUser = userRepository.findById(requesterId).orElseThrow(() -> new AuthException("로그인하세요."));

        Optional<BlockUserEntity> blockUser = blockUserRepository.findByRequesterIdAndTargetId(requesterId, targetId);
        UserEntity targetUser = userRepository.findById(targetId).orElseThrow(() -> new AuthException("존재하지 않는 유저입니다."));

        if(targetUser.isDelete()) throw new AuthException("이미 탈퇴한 회원입니다.");

        if (blockUser.isPresent()) {
            throw new AuthException("이미 차단한 유저입니다.");
        } else {
            BlockUserEntity blockUserEntity = new BlockUserEntity(requestUser.getId(), targetId);
            blockUserRepository.save(blockUserEntity);
            return new EmptyResponse();
        }
    }

//    public EmptyResponse unblockUser(Long requesterId, Long targetId){
//        UserEntity requestUser = userRepository.findById(requesterId).orElseThrow(() -> new AuthException("로그인하세요."));
//        Optional<BlockUserEntity> blockUser = blockUserRepository.findByRequesterIdAndTargetId(requesterId, targetId);
//        UserEntity user = userRepository.findById(targetId).orElseThrow(() -> new AuthException("존재하지 않는 유저입니다."));
//
//        if (blockUser.isPresent()) {
//            BlockUserEntity blockUserEntity = new BlockUserEntity(requestUser.getId(), targetId);
//            blockUserRepository.delete(blockUserEntity);
//            return new EmptyResponse();
//        } else {
//            throw new AuthException("차단한 적 없는 유저입니다.");
//        }
//    }
}

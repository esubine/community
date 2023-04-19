package esubine.community.badge;

import esubine.community.badge.model.BadgeEntity;
import esubine.community.badge.model.BadgeRepository;
import esubine.community.badge.model.UserBadgeEntity;
import esubine.community.badge.model.UserBadgeRepository;
import esubine.community.board.model.BoardRepository;
import esubine.community.comment.model.CommentRepository;
import esubine.community.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final UserBadgeRepository userBadgeRepository;
    private final BadgeRepository badgeRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    public void refreshBadge(Long userId) {
        Set<BadgeEntity> badgeEntities = badgeRepository.getByBadgeId(userId);

        UserEntity userEntity = UserEntity.of(userId);

        badgeEntities.stream()
                .filter((e) -> e.getStartDate() == null || e.getStartDate().isBefore(LocalDateTime.now()))
                .filter((e) -> e.getEndDate() == null || e.getEndDate().isAfter(LocalDateTime.now()))
                .filter((e) -> e.getBoardCount() == null || e.getBoardCount() <= boardRepository.countByUserId(userId))
//                .filter((e) -> e.getCommentCount() == null || e.getCommentCount() <= commentRepository.countByUserId(userId))
                .filter((e) -> e.getLikeCount() == null || e.getLikeCount() <= boardRepository.sumLikeCountByUserId(userId))
                .forEach((e) -> {
                    userBadgeRepository.save(new UserBadgeEntity(userEntity, e));
                });
    }


}

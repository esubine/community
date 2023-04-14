package esubine.community.user.model;

import esubine.community.badge.model.UserBadgeEntity;
import esubine.community.hashtag.model.BoardHashTagEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name="real_name")
    private String realName;

    @Column(name="nickname")
    private String nickname;

    @Column(name="login_id")
    private String loginId;

    @Column(name="login_password")
    private String loginPassword;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "badge", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<UserBadgeEntity> userBadges = new HashSet<>();

    public static UserEntity of(String realName, String nickname, String loginId, String loginPassword) {
        UserEntity user = new UserEntity();
        user.realName = realName;
        user.nickname = nickname;
        user.loginId = loginId;
        user.loginPassword = loginPassword;
        return user;
    }

    public static UserEntity of(Long userId) {
        UserEntity user = new UserEntity();
        user.id = userId;
        return user;
    }
}

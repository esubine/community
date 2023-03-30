package esubine.community.db.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="user")
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


    public static UserEntity of(String realName, String nickname, String loginId, String loginPassword) {
        UserEntity user = new UserEntity();
        user.realName = realName;
        user.nickname = nickname;
        user.loginId = loginId;
        user.loginPassword = loginPassword;
        return user;
    }
}

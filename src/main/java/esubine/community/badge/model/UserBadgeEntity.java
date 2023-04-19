package esubine.community.badge.model;

import esubine.community.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@Table(name = "user_badge")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserBadgeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_badge_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id")
    private BadgeEntity badge;

    public UserBadgeEntity(UserEntity user, BadgeEntity badge) {
        this.user = user;
        this.badge = badge;
    }

}


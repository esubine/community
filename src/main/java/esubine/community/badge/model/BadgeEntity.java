package esubine.community.badge.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.LazyInitializationException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "badge")
@EntityListeners(AuditingEntityListener.class)
public class BadgeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long badgeId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @CreatedDate
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @LastModifiedDate
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Nullable
    @Column(name = "board_count")
    private Integer boardCount;

    @Nullable
    @Column(name = "comment_count")
    private Integer commentCount;

    @Nullable
    @Column(name = "like_count")
    private Integer likeCount;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "badge", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<UserBadgeEntity> userBadges = new HashSet<>();

    public Set<Long> getUserId() {
        try {
            return userBadges.stream()
                    .map((e) -> e.getUser().getId())
                    .collect(Collectors.toSet());
        } catch (LazyInitializationException e) {
            return null;
        }
    }
}

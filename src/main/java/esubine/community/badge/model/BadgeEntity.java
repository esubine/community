package esubine.community.badge.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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

    @Nullable
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


}

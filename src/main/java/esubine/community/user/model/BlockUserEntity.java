package esubine.community.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="block_user")
@EntityListeners(AuditingEntityListener.class)
public class BlockUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_user_id")
    private Long blockUserId;
    @Column(name="requester_id")
    private Long requesterId;
    @Column(name = "target_id")
    private Long targetId;

    public BlockUserEntity(Long requesterId, Long targetId){
        this.requesterId = requesterId;
        this.targetId = targetId;
    }
}

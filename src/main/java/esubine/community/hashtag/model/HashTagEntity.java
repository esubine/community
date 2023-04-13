package esubine.community.hashtag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Table(name="hashtag")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class HashTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long hashTagId;

    @Column(name="name")
    private String name;

    public HashTagEntity(String name){
        this.name = name;
    }
}

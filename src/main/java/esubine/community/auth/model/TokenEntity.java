package esubine.community.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="token")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;
    @Column(name="token")
    private String token;
    @Column(name="user_id")
    private Long userId;
    @Column(name="is_delete")
    private boolean isDelete;

    public TokenEntity(String token, Long userId){
        this.token = token;
        this.userId = userId;
    }

}

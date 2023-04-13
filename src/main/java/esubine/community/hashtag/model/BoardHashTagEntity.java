package esubine.community.hashtag.model;

import esubine.community.board.model.BoardEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Table(name="board_hashtag")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BoardHashTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_hashtag_id")
    private Long boardHashTagId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private HashTagEntity hashtag;

    public BoardHashTagEntity(BoardEntity board, HashTagEntity hashtag){
        this.board = board;
        this.hashtag = hashtag;
    }

}

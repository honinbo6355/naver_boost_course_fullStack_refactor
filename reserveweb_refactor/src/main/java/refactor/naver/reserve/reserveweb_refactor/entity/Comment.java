package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reservation_user_comment")
@Getter
@Setter
@ToString
public class Comment extends SystemDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    @OneToOne(mappedBy = "comment", fetch = FetchType.LAZY)
    private CommentImage commentImage;

    private double score;
    private String comment;
}

package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "reservation_user_comment_image")
@Getter
@Setter
@ToString
public class CommentImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_user_comment_id")
    private Comment comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;
}

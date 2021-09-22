package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "reservation_info_price")
@Getter
@Setter
public class ReservationInfoPrice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice;

    private int count;
}

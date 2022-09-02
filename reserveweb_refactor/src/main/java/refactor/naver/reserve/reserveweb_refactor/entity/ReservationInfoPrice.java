package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "reservation_info_price")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationInfoPrice extends SystemDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice;

    private int count;

    @Builder
    public ReservationInfoPrice(ReservationInfo reservationInfo, ProductPrice productPrice, int count) {
        this.reservationInfo = reservationInfo;
        this.productPrice = productPrice;
        this.count = count;
    }
}

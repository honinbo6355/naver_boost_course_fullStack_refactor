package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reservation_info")
@Getter
@Setter
public class ReservationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "display_info_id")
    private DisplayInfo displayInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservationInfo")
    private Set<ReservationInfoPrice> reservationInfoPrices;

    @Column(name = "reservation_name")
    private String reservationName;

    @Column(name = "reservation_tel")
    private String reservationTel;

    @Column(name = "reservation_email")
    private String reservationEmail;

    @Column(name = "reservation_date")
    private String reservationDate;

    @Column(name = "cancel_flag")
    private boolean cancelFlag;

    @Embedded
    private SystemDate systemDate;
}

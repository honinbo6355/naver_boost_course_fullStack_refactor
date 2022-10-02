package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_tel")
    private String buyerTel;

    @Column(name = "imp_uid")
    private String impUid;

    @Column(name = "merchant_uid")
    private String merchantUid;

    @Column(name = "name")
    private String name;

    @Column(name = "paid_amount")
    private Long paidAmount;

    @Column(name = "pay_method")
    private String payMethod;

    @Column(name = "pg_provider")
    private String pgProvider;

    @Column(name = "pg_tid")
    private String pgTid;

    @Column(name = "pg_type")
    private String pgType;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "status")
    private String status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orders")
    private ReservationInfo reservationInfo;
}

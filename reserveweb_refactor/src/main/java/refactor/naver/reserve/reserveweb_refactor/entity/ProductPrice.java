package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_price")
@Getter
@Setter
public class ProductPrice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productPrice")
    private Set<ReservationInfoPrice> reservationInfoPrices;

    private String priceTypeName;
    private int price;
    private double discountRate;

    @Embedded
    private SystemDate systemDate;
}

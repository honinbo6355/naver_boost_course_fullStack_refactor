package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "display_info")
@Getter
@Setter
@ToString
public class DisplayInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "place_lot")
    private String placeLot;

    @Column(name = "place_street")
    private String placeStreet;

    @Column(name = "tel")
    private String telephone;

    private String homepage;
    private String email;

    @Embedded
    private SystemDate systemDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "display_info")
@Getter
@Setter
public class DisplayInfo extends SystemDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String homepage;
    private String openingHours;
    private String placeLot;
    private String placeName;
    private String placeStreet;
    private String productContent;
    private String productDescription;
    private String productEvent;
    private String telephone;

    @ManyToOne
    private Product product;
}

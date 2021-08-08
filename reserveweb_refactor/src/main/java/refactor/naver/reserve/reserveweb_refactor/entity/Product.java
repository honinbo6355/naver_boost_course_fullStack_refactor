package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString(exclude = "displayInfoList")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private String content;
    private String event;

    @Embedded
    private SystemDate systemDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<DisplayInfo> displayInfoList;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product")
    private Promotion promotion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductImage> productImageList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ReservationInfo> reservationInfos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Comment> comments;
}

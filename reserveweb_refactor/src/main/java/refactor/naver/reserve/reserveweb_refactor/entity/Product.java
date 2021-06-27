package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private String content;
    private int displayInfoId;
    private String placeName;
    private String imageUrl;

    @ManyToOne
    private Category category;

    @OneToMany
    private List<DisplayInfo> displayInfoList;
}

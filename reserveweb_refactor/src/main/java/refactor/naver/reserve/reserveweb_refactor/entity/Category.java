package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private long count;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> productList;

    public Category(int id, String name, long count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}


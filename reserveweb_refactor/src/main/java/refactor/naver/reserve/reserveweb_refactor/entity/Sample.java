package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sample")
@Getter @Setter
public class Sample {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
}

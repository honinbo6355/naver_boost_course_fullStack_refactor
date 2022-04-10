package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority_name")
    private String authorityName;
}

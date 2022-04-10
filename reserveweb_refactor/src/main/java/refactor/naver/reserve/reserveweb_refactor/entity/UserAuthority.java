package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class UserAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @Builder
    public UserAuthority(Authority authority) {
        this.authority = authority;
    }
}

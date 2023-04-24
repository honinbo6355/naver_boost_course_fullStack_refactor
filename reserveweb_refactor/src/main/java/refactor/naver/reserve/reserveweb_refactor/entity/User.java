package refactor.naver.reserve.reserveweb_refactor.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends SystemDate {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAuthority> userAuthorities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ReservationInfo> reservationInfoList;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User addUserAuthority(UserAuthority userAuthority) {
        this.userAuthorities.add(userAuthority);
        userAuthority.setUser(this);

        return this;
    }
}

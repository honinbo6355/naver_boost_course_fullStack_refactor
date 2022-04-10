package refactor.naver.reserve.reserveweb_refactor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @Getter
    @Setter
    public static class Login {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    public static class ReIssue {
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Setter
    public static class Signup {
        private String email;
        private String password;
    }
}

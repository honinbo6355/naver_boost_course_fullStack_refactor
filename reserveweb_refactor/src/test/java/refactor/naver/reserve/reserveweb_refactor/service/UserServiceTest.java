package refactor.naver.reserve.reserveweb_refactor.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.dto.UserRequestDto;
import refactor.naver.reserve.reserveweb_refactor.entity.User;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRequestDto.Signup createUserRequest() {
        UserRequestDto.Signup signup = new UserRequestDto.Signup();

        signup.setEmail("testUser3@naver.com");
        signup.setPassword("testUser3");

        return signup;
    }

    @Test
    @Transactional
    public void 회원가입_테스트() {
        UserRequestDto.Signup signup = this.createUserRequest();

        try {
            userService.signup(signup);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("패스워드 인코딩 테스트")
    public void 패스워드_인코딩_테스트() {
        String rawPassword = "testUser3";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        Assertions.assertThat(rawPassword).isNotEqualTo(encodedPassword);
        Assertions.assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
    }

    @Test
    @DisplayName("로그인 테스트")
    public void 로그인_테스트() throws Exception {
        String email = "testUser3@naver.com";
        String password = "testUser3";

        User user = userService.getUser(email, password);
        Assertions.assertThat(user).isNotNull();
    }
}

package refactor.naver.reserve.reserveweb_refactor.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.dto.UserRequestDto;
import refactor.naver.reserve.reserveweb_refactor.entity.User;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

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
}

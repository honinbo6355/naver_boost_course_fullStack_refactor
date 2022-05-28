package refactor.naver.reserve.reserveweb_refactor.service;

import refactor.naver.reserve.reserveweb_refactor.dto.UserRequestDto;
import refactor.naver.reserve.reserveweb_refactor.entity.User;

public interface UserService {
    void signup(UserRequestDto.Signup signup) throws Exception;
    User getUser(String email, String password) throws Exception;
}

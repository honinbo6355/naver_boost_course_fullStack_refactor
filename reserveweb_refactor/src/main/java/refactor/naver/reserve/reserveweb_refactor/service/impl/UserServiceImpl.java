package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.dto.UserRequestDto;
import refactor.naver.reserve.reserveweb_refactor.entity.Authority;
import refactor.naver.reserve.reserveweb_refactor.entity.User;
import refactor.naver.reserve.reserveweb_refactor.entity.UserAuthority;
import refactor.naver.reserve.reserveweb_refactor.errors.CustomException;
import refactor.naver.reserve.reserveweb_refactor.errors.ErrorCode;
import refactor.naver.reserve.reserveweb_refactor.repository.AuthorityRepository;
import refactor.naver.reserve.reserveweb_refactor.repository.UserRepository;
import refactor.naver.reserve.reserveweb_refactor.service.UserService;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    @Transactional
    public void signup(UserRequestDto.Signup signup) throws Exception {
        if (userRepository.findOneWithUserAuthoritiesByEmail(signup.getEmail()).orElse(null) != null) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_USER);
        }

        Authority authority = authorityRepository.findByAuthorityName("ROLE_USER");

        UserAuthority userAuthority = UserAuthority.builder()
                .authority(authority)
                .build();

        User newUser = User.builder()
                .email(signup.getEmail())
                .password(passwordEncoder.encode(signup.getPassword()))
                .build();

        newUser.addUserAuthority(userAuthority);

        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public User getUser(String email, String password) throws Exception {
        User user = userRepository.findOneWithUserAuthoritiesByEmail(email).orElse(null);

        if (user == null) {
            throw new RuntimeException("로그인 정보가 일치하지 않습니다.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("로그인 정보가 일치하지 않습니다.");
        }

        return user;
    }
}

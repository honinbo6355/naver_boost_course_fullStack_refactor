package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.entity.Authority;
import refactor.naver.reserve.reserveweb_refactor.entity.User;
import refactor.naver.reserve.reserveweb_refactor.repository.UserRepository;
import refactor.naver.reserve.reserveweb_refactor.service.UserService;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void signup(User user) throws Exception {
        if (userRepository.findOneWithAuthoritiesByEmail(user.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User newUser = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .authorities(Collections.singleton(authority))
                .build();

        userRepository.save(newUser);
    }
}

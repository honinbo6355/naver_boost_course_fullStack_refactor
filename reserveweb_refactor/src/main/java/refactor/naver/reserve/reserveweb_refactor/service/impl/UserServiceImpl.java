package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.dto.UserRequestDto;
import refactor.naver.reserve.reserveweb_refactor.entity.Authority;
import refactor.naver.reserve.reserveweb_refactor.entity.User;
import refactor.naver.reserve.reserveweb_refactor.entity.UserAuthority;
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
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
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
}

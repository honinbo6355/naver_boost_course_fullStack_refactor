package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import refactor.naver.reserve.reserveweb_refactor.entity.User;
import refactor.naver.reserve.reserveweb_refactor.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component("UserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 로그인할 때 security 내부적으로 loadUserByUsername 메소드를 통해 검증을 한다.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findOneWithUserAuthoritiesByEmail(email)
                .map(user -> createUser(user))
                .orElseThrow(() -> new UsernameNotFoundException(email + "은 존재하지 않습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
                .map(userAuthority -> new SimpleGrantedAuthority(userAuthority.getAuthority().getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities);
    }
}

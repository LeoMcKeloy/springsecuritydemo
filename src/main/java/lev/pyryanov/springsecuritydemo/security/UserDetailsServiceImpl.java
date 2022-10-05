package lev.pyryanov.springsecuritydemo.security;

import lev.pyryanov.springsecuritydemo.model.User;
import lev.pyryanov.springsecuritydemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        User user = repository.findByEmail (email).orElseThrow (() ->
                new UsernameNotFoundException ("User doesn't exist"));
        return SecurityUser.fromUser (user);
    }
}

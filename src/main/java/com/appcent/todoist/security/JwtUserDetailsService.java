package com.appcent.todoist.security;

import com.appcent.todoist.model.User;
import com.appcent.todoist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Long identityNo = Long.valueOf(username);

        User user = userRepository.findByIdentityNo(identityNo);

        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserByUserId(Long id) {

        User user = userRepository.getById(id);

        return JwtUserDetails.create(user);
    }
}

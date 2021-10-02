package com.hotelproject.app.service.impl;

import com.hotelproject.app.model.Role;
import com.hotelproject.app.model.User;
import com.hotelproject.app.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User was not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getIsEnabled(),
                false,
                false,
                false,
                getUserRoles(user)
        );

    }

    public List<SimpleGrantedAuthority> getUserRoles(User user) {
        Set<Role> userRoles = user.getRoles();
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

         userRoles.forEach(role -> {
             simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName())));
         });

         return simpleGrantedAuthorities;
    }
}

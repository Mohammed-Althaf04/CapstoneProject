package com.wip.smartparking.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wip.smartparking.entity.User;
import com.wip.smartparking.repository.UserRepository;
/**
 * Custom implementation of UserDetailsService to load user credentials from the database during authentication.
 *
 * @author althaf
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fallback for default admin
        if ("admin".equals(username)) {
            return org.springframework.security.core.userdetails.User
                    .withUsername("admin")
                    .password("admin123")
                    .authorities("ROLE_ADMIN")
                    .build();
        }
        // Fallback for default customer/user
        if ("customer".equals(username)) {
            return org.springframework.security.core.userdetails.User
                    .withUsername("customer")
                    .password("customer123")
                    .authorities("ROLE_USER")
                    .build();
        }

        User user = userRepository.findByEmailOrName(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email or name: " + username));

        String roleName = "ROLE_" + user.getRole().name(); // ROLE_ADMIN or ROLE_USER

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(roleName)
                .build();
    }
}

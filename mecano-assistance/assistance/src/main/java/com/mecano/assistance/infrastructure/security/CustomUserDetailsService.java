package com.mecano.assistance.infrastructure.security;

import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SpringDataUserRepository userRepository;

    public CustomUserDetailsService(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        var authorities = new ArrayList<SimpleGrantedAuthority>();

        authorities.add(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );

        RolePermissionMapper.permissionsFor(user.getRole())
                .forEach(permission ->
                        authorities.add(
                                new SimpleGrantedAuthority("PERMISSION_" + permission.name())
                        )
                );
        System.out.println("AUTHORITIES = " + authorities);
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .disabled(!user.isActive())
                //.roles(user.getRole().name())
                .authorities(authorities)
                .build();
    }
}
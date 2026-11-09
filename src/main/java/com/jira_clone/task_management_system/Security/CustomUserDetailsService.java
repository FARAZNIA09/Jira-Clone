package com.jira_clone.task_management_system.Security;

import com.jira_clone.task_management_system.Entity.UserEntity;
import com.jira_clone.task_management_system.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        String roleName = user.getRole().getName();

        String authority = roleName.startsWith("ROLE_")
                ? roleName
                : "ROLE_" + roleName;

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(List.of(
                        new SimpleGrantedAuthority(authority)))
                .disabled(!Boolean.TRUE.equals(user.getIsActive()))
                .build();
    }
}

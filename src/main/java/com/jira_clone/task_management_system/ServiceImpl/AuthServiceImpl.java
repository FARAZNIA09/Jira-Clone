
package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.AuthRequestDTO;
import com.jira_clone.task_management_system.DTO.AuthResponseDTO;
import com.jira_clone.task_management_system.Entity.UserEntity;
import com.jira_clone.task_management_system.Repository.UserRepository;
import com.jira_clone.task_management_system.Security.JwtService;
import com.jira_clone.task_management_system.Service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional(readOnly = true)
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {

        UserEntity user = userRepository
                .findByEmail(authRequestDTO.getEmail())
                .orElseThrow(() ->
                        new BadCredentialsException(
                                "Invalid email or password"));

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new DisabledException("User account is inactive");
        }

        if (user.getPassword() == null
                || !passwordEncoder.matches(
                authRequestDTO.getPassword(),
                user.getPassword())) {
            throw new BadCredentialsException(
                    "Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponseDTO.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }
}

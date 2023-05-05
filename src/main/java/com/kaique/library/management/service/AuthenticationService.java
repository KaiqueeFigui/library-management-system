package com.kaique.library.management.service;

import com.kaique.library.management.config.security.MyUserDetails;
import com.kaique.library.management.dto.auth.AuthenticationResponse;
import com.kaique.library.management.dto.auth.LoginRequest;
import com.kaique.library.management.repository.UserRepository;
import com.kaique.library.management.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        return AuthenticationResponse.builder()
                .token(jwtUtil.generateAccessToken(new MyUserDetails(user)))
                .build();
    }
}

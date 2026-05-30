package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.infrastructure.persistence.entity.UserEntity;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import com.mecano.assistance.infrastructure.security.AuthProvider;
import com.mecano.assistance.infrastructure.security.JwtService;
import com.mecano.assistance.interfaces.rest.dto.AuthResponse;
import com.mecano.assistance.interfaces.rest.dto.LoginRequest;
import com.mecano.assistance.interfaces.rest.dto.RegisterRequest;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final SpringDataUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            SpringDataUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("Email already used");
        }

        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        user.setProvider(AuthProvider.LOCAL);
        user.setActive(true);

        UserEntity saved = userRepository.save(user);
        String token = jwtService.generateToken(saved);

        /*return new AuthResponse(
                saved.getId(),
                saved.getEmail(),
                saved.getRole().name(),
                token
        );*/
        return ApiResponse.success(
                "Authentication request register successfully",
                new AuthResponse(
                        saved.getId(),
                        saved.getEmail(),
                        saved.getRole().name(),
                        token
                )
        );
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException("Invalid credentials");
        }

        if (!user.isActive()) {
            throw new BusinessException("Account disabled");
        }

        String token = jwtService.generateToken(user);

        return ApiResponse.success(
                "Login successful",
                new AuthResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getRole().name(),
                        token
                )
        );
    }
}
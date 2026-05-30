package com.mecano.assistance.interfaces.rest.dto;

import java.util.UUID;

public record AuthResponse(
        UUID userId,
        String email,
        String role,
        String token
) {}
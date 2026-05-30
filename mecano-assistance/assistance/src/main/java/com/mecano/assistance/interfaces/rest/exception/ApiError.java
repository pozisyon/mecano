package com.mecano.assistance.interfaces.rest.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        boolean success,
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        List<String> details
) {
}
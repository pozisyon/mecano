package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.command.CreateBreakdownRequestCommand;
import com.mecano.assistance.application.usecase.CreateBreakdownRequestUseCase;
import com.mecano.assistance.application.usecase.FindMatchingMechanicsUseCase;
import com.mecano.assistance.domain.valueobject.Location;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import com.mecano.assistance.interfaces.rest.dto.CreateBreakdownRequestDto;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/breakdowns")
public class BreakdownController {

    private final CreateBreakdownRequestUseCase createBreakdownRequestUseCase;
    private final FindMatchingMechanicsUseCase findMatchingMechanicsUseCase;
    private final SpringDataUserRepository userRepository;
    public BreakdownController(CreateBreakdownRequestUseCase createBreakdownRequestUseCase,
                               FindMatchingMechanicsUseCase findMatchingMechanicsUseCase,SpringDataUserRepository userRepository) {
        this.createBreakdownRequestUseCase = createBreakdownRequestUseCase;
        this.findMatchingMechanicsUseCase = findMatchingMechanicsUseCase;
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<?> create(
            @Valid @RequestBody CreateBreakdownRequestDto dto,
            Authentication authentication
    ) {
        var user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        var command = new CreateBreakdownRequestCommand(
                user.getId(),
                dto.vehicleId(),
                dto.type(),
                dto.description(),
                new Location(dto.latitude(), dto.longitude())
        );

        return ApiResponse.success(
                "Breakdown request created successfully",
                createBreakdownRequestUseCase.execute(command)
        );
    }

    @GetMapping("/{id}/matches")
    public ApiResponse<?> findMatches(@PathVariable UUID id) {
       // return findMatchingMechanicsUseCase.execute(id);
        return ApiResponse.success(
                "Breakdown request findMatches successfully",
                findMatchingMechanicsUseCase.execute(id)
        );
    }
}

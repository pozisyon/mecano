package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.command.CreateBreakdownRequestCommand;
import com.mecano.assistance.application.usecase.CreateBreakdownRequestUseCase;
import com.mecano.assistance.application.usecase.FindMatchingMechanicsUseCase;
import com.mecano.assistance.domain.valueobject.Location;
import com.mecano.assistance.interfaces.rest.dto.CreateBreakdownRequestDto;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/breakdowns")
public class BreakdownController {

    private final CreateBreakdownRequestUseCase createBreakdownRequestUseCase;
    private final FindMatchingMechanicsUseCase findMatchingMechanicsUseCase;

    public BreakdownController(CreateBreakdownRequestUseCase createBreakdownRequestUseCase,
                               FindMatchingMechanicsUseCase findMatchingMechanicsUseCase) {
        this.createBreakdownRequestUseCase = createBreakdownRequestUseCase;
        this.findMatchingMechanicsUseCase = findMatchingMechanicsUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<?> create(@Valid @RequestBody CreateBreakdownRequestDto dto) {
        var command = new CreateBreakdownRequestCommand(
                dto.driverId(),
                dto.vehicleId(),
                dto.type(),
                dto.description(),
                new Location(dto.latitude(), dto.longitude()),
                dto.status(),
                dto.createdAt()
        );

        //return createBreakdownRequestUseCase.execute(command);
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

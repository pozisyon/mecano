package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.command.PayInterventionCommand;
import com.mecano.assistance.application.usecase.GetMyPaymentsUseCase;
import com.mecano.assistance.application.usecase.PayInterventionUseCase;
import com.mecano.assistance.domain.valueobject.Money;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import com.mecano.assistance.interfaces.rest.dto.PayInterventionDto;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PayInterventionUseCase payInterventionUseCase;
    private final GetMyPaymentsUseCase getMyPaymentsUseCase;
    private final SpringDataUserRepository userRepository;
    public PaymentController(PayInterventionUseCase payInterventionUseCase, GetMyPaymentsUseCase getMyPaymentsUseCase,SpringDataUserRepository userRepository) {
        this.payInterventionUseCase = payInterventionUseCase;
        this.getMyPaymentsUseCase = getMyPaymentsUseCase;
        this.userRepository = userRepository;
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<?> pay(@Valid @RequestBody PayInterventionDto dto) {
        var result = payInterventionUseCase.execute(
                new PayInterventionCommand(
                        dto.interventionId(),
                        new Money(dto.amount(), dto.currency()),
                        dto.method()
                )
        );

        return ApiResponse.success("Payment processed successfully", result);
    }
    @GetMapping("/my")
    public ApiResponse<?> myPayments(Authentication authentication) {
        var user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        return ApiResponse.success(
                "Payments retrieved successfully",
                getMyPaymentsUseCase.execute(user.getId())
        );
    }
}
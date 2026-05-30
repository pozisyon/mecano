package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.command.PayInterventionCommand;
import com.mecano.assistance.application.usecase.PayInterventionUseCase;
import com.mecano.assistance.domain.valueobject.Money;
import com.mecano.assistance.interfaces.rest.dto.PayInterventionDto;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PayInterventionUseCase payInterventionUseCase;

    public PaymentController(PayInterventionUseCase payInterventionUseCase) {
        this.payInterventionUseCase = payInterventionUseCase;
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
}
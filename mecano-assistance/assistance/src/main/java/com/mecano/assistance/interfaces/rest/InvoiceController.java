package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.usecase.GetInvoiceUseCase;
import com.mecano.assistance.application.usecase.GetInvoicesByInterventionUseCase;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final GetInvoiceUseCase getInvoiceUseCase;
    private final GetInvoicesByInterventionUseCase getInvoicesByInterventionUseCase;
    private final GetInvoiceUseCase getMyInvoicesUseCase;
    private final SpringDataUserRepository userRepository;
    public InvoiceController(
            GetInvoiceUseCase getInvoiceUseCase,
            GetInvoicesByInterventionUseCase getInvoicesByInterventionUseCase,SpringDataUserRepository userRepository,GetInvoiceUseCase getMyInvoicesUseCase
    ) {
        this.getInvoiceUseCase = getInvoiceUseCase;
        this.getInvoicesByInterventionUseCase = getInvoicesByInterventionUseCase;
        this.userRepository = userRepository;
        this.getMyInvoicesUseCase = getMyInvoicesUseCase;
    }


    @GetMapping("/my")
    public ApiResponse<?> myInvoices(Authentication authentication) {
        var user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        return ApiResponse.success(
                "Invoices retrieved successfully",
                getMyInvoicesUseCase.execute(user.getId())
        );

    }

    @GetMapping("/{id}")
    public ApiResponse<?> getById(@PathVariable UUID id) {
        return ApiResponse.success(
                "Invoice retrieved successfully",
                getInvoiceUseCase.execute(id)
        );
    }

    @GetMapping("/intervention/{interventionId}")
    public ApiResponse<?> getByIntervention(@PathVariable UUID interventionId) {
        return ApiResponse.success(
                "Invoices retrieved successfully",
                getInvoicesByInterventionUseCase.execute(interventionId)
        );
    }


}
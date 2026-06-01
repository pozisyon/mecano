package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.usecase.GetInvoiceUseCase;
import com.mecano.assistance.application.usecase.GetInvoicesByInterventionUseCase;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final GetInvoiceUseCase getInvoiceUseCase;
    private final GetInvoicesByInterventionUseCase getInvoicesByInterventionUseCase;

    public InvoiceController(
            GetInvoiceUseCase getInvoiceUseCase,
            GetInvoicesByInterventionUseCase getInvoicesByInterventionUseCase
    ) {
        this.getInvoiceUseCase = getInvoiceUseCase;
        this.getInvoicesByInterventionUseCase = getInvoicesByInterventionUseCase;
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
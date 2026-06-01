package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.usecase.ApproveMechanicUseCase;
import com.mecano.assistance.application.usecase.GetPendingMechanicsUseCase;
import com.mecano.assistance.application.usecase.RejectMechanicUseCase;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/mechanics")
public class AdminMechanicController {

    private final GetPendingMechanicsUseCase getPendingMechanicsUseCase;
    private final ApproveMechanicUseCase approveMechanicUseCase;
    private final RejectMechanicUseCase rejectMechanicUseCase;

    public AdminMechanicController(
            GetPendingMechanicsUseCase getPendingMechanicsUseCase,
            ApproveMechanicUseCase approveMechanicUseCase,
            RejectMechanicUseCase rejectMechanicUseCase
    ) {
        this.getPendingMechanicsUseCase = getPendingMechanicsUseCase;
        this.approveMechanicUseCase = approveMechanicUseCase;
        this.rejectMechanicUseCase = rejectMechanicUseCase;
    }

    @GetMapping("/pending")
    @PreAuthorize("hasAuthority('PERMISSION_MECHANIC_READ')")
    public ApiResponse<?> pending() {
        return ApiResponse.success(
                "Pending mechanics retrieved successfully",
                getPendingMechanicsUseCase.execute()
        );
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('PERMISSION_MECHANIC_VALIDATE')")
    public ApiResponse<?> approve(@PathVariable UUID id) {
        approveMechanicUseCase.execute(id);

        return ApiResponse.success(
                "Mechanic approved successfully",
                null
        );
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasAuthority('PERMISSION_MECHANIC_REJECT')")
    public ApiResponse<?> reject(@PathVariable UUID id) {
        rejectMechanicUseCase.execute(id);

        return ApiResponse.success(
                "Mechanic rejected successfully",
                null
        );
    }
}
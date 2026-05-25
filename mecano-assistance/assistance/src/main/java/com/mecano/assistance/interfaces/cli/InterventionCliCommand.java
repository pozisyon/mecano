package com.mecano.assistance.interfaces.cli;

import com.mecano.assistance.application.command.AcceptInterventionCommand;
import com.mecano.assistance.application.command.UpdateInterventionStatusCommand;
import com.mecano.assistance.application.usecase.AcceptInterventionUseCase;
import com.mecano.assistance.application.usecase.UpdateInterventionStatusUseCase;
import com.mecano.assistance.domain.model.InterventionStatus;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.UUID;

@ShellComponent
public class InterventionCliCommand {

    private final AcceptInterventionUseCase acceptInterventionUseCase;
    private final UpdateInterventionStatusUseCase updateInterventionStatusUseCase;

    public InterventionCliCommand(
            AcceptInterventionUseCase acceptInterventionUseCase,
            UpdateInterventionStatusUseCase updateInterventionStatusUseCase
    ) {
        this.acceptInterventionUseCase = acceptInterventionUseCase;
        this.updateInterventionStatusUseCase = updateInterventionStatusUseCase;
    }

    @ShellMethod(key = "intervention accept", value = "Accept an intervention")
    public String accept(
            @ShellOption UUID breakdownId,
            @ShellOption UUID mechanicId
    ) {
        var result = acceptInterventionUseCase.execute(
                new AcceptInterventionCommand(breakdownId, mechanicId)
        );

        return "Intervention accepted: " + result.interventionId()
                + " | status: " + result.status();
    }

    @ShellMethod(key = "intervention status", value = "Update intervention status")
    public String updateStatus(
            @ShellOption UUID interventionId,
            @ShellOption InterventionStatus status
    ) {
        var result = updateInterventionStatusUseCase.execute(
                new UpdateInterventionStatusCommand(interventionId, status)
        );

        return "Intervention updated: " + result.interventionId()
                + " | status: " + result.status();
    }
}
package com.mecano.assistance.interfaces.cli;

import com.mecano.assistance.application.command.CreateBreakdownRequestCommand;
import com.mecano.assistance.application.usecase.CreateBreakdownRequestUseCase;
import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.model.BreakdownType;
import com.mecano.assistance.domain.valueobject.Location;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.util.UUID;

@ShellComponent
public class BreakdownCliCommand {

    private final CreateBreakdownRequestUseCase createBreakdownRequestUseCase;

    public BreakdownCliCommand(CreateBreakdownRequestUseCase createBreakdownRequestUseCase) {
        this.createBreakdownRequestUseCase = createBreakdownRequestUseCase;
    }

    @ShellMethod(key = "breakdown create", value = "Create a breakdown request")
    public String createBreakdown(
            @ShellOption UUID driverId,
            @ShellOption UUID vehicleId,
            @ShellOption BreakdownType type,
            @ShellOption String description,
            @ShellOption double latitude,
            @ShellOption(arity = 1) String longitude,
            @ShellOption BreakdownStatus status,
            @ShellOption LocalDateTime createdAt
    ) {
        double parsedLongitude = Double.parseDouble(longitude);

        var result = createBreakdownRequestUseCase.execute(
                new CreateBreakdownRequestCommand(
                        driverId,
                        vehicleId,
                        type,
                        description,
                        new Location(latitude, parsedLongitude)
                      //  status,
                        //createdAt
                )
        );

        return "Breakdown created: " + result.requestId()
                + " | status: " + result.status();
    }
}
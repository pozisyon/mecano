package com.mecano.assistance.interfaces.cli;

import com.mecano.assistance.application.command.PayInterventionCommand;
import com.mecano.assistance.application.usecase.PayInterventionUseCase;
import com.mecano.assistance.domain.valueobject.Money;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.util.UUID;

@ShellComponent
public class PaymentCliCommand {

    private final PayInterventionUseCase payInterventionUseCase;

    public PaymentCliCommand(PayInterventionUseCase payInterventionUseCase) {
        this.payInterventionUseCase = payInterventionUseCase;
    }

    @ShellMethod(key = "payment pay", value = "Pay an intervention")
    public String pay(
            @ShellOption UUID interventionId,
            @ShellOption BigDecimal amount,
            @ShellOption(defaultValue = "USD") String currency,
            @ShellOption(defaultValue = "CARD") String method
    ) {
        var result = payInterventionUseCase.execute(
                new PayInterventionCommand(
                        interventionId,
                        new Money(amount, currency),
                        method
                )
        );

        return "Payment: " + result.paymentId()
                + " | status: " + result.paymentStatus();
    }
}
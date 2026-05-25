package com.mecano.assistance.interfaces.cli;

import com.mecano.assistance.application.usecase.FindMatchingMechanicsUseCase;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.UUID;

@ShellComponent
public class MatchingCliCommand {

    private final FindMatchingMechanicsUseCase findMatchingMechanicsUseCase;

    public MatchingCliCommand(FindMatchingMechanicsUseCase findMatchingMechanicsUseCase) {
        this.findMatchingMechanicsUseCase = findMatchingMechanicsUseCase;
    }

    @ShellMethod(key = "breakdown matches", value = "Find matching mechanics for a breakdown")
    public String findMatches(@ShellOption UUID breakdownId) {
        var mechanics = findMatchingMechanicsUseCase.execute(breakdownId);

        if (mechanics.isEmpty()) {
            return "No mechanic found.";
        }

        StringBuilder output = new StringBuilder();

        mechanics.forEach(m ->
                output.append("Mechanic: ")
                        .append(m.getId())
                        .append(" | ")
                        .append(m.getFullName())
                        .append(" | speciality: ")
                        .append(m.getSpeciality())
                        .append(" | rating: ")
                        .append(m.getRating())
                        .append("\n")
        );

        return output.toString();
    }
}
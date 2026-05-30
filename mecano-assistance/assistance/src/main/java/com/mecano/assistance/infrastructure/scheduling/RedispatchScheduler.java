package com.mecano.assistance.infrastructure.scheduling;

import com.mecano.assistance.application.usecase.RedispatchPendingBreakdownsUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RedispatchScheduler {

    private final RedispatchPendingBreakdownsUseCase redispatchUseCase;

    public RedispatchScheduler(RedispatchPendingBreakdownsUseCase redispatchUseCase) {
        this.redispatchUseCase = redispatchUseCase;
    }

    @Scheduled(fixedRate = 60000)
    public void redispatchPendingBreakdowns() {
        redispatchUseCase.execute();
    }
}
package com.mecano.assistance.bootstrap.config;

import com.mecano.assistance.application.usecase.*;
import com.mecano.assistance.domain.port.*;
import com.mecano.assistance.domain.service.MatchingDomainService;
import com.mecano.assistance.domain.strategy.MatchingStrategy;
import com.mecano.assistance.domain.strategy.NearestMechanicStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateBreakdownRequestUseCase createBreakdownRequestUseCase(
            BreakdownRepositoryPort breakdownRepositoryPort
    ) {
        return new CreateBreakdownRequestUseCase(breakdownRepositoryPort);
    }

    @Bean
    public MatchingStrategy matchingStrategy(GeoDistancePort geoDistancePort) {
        return new NearestMechanicStrategy(geoDistancePort);
    }

    @Bean
    public MatchingDomainService matchingDomainService(MatchingStrategy matchingStrategy) {
        return new MatchingDomainService(matchingStrategy);
    }


    @Bean
    public FindMatchingMechanicsUseCase findMatchingMechanicsUseCase(
            BreakdownRepositoryPort breakdownRepositoryPort,
            MechanicRepositoryPort mechanicRepositoryPort,
            MatchingDomainService matchingDomainService,
            NotificationPort notificationPort
    ) {
        return new FindMatchingMechanicsUseCase(
                breakdownRepositoryPort,
                mechanicRepositoryPort,
                matchingDomainService,
                notificationPort
        );
    }

    @Bean
    public AcceptInterventionUseCase acceptInterventionUseCase(
            BreakdownRepositoryPort breakdownRepositoryPort,
            MechanicRepositoryPort mechanicRepositoryPort,
            InterventionRepositoryPort interventionRepositoryPort,
            NotificationPort notificationPort
    ) {
        return new AcceptInterventionUseCase(
                breakdownRepositoryPort,
                mechanicRepositoryPort,
                interventionRepositoryPort,
                notificationPort
        );
    }

    @Bean
    public UpdateInterventionStatusUseCase updateInterventionStatusUseCase(
            InterventionRepositoryPort interventionRepositoryPort
    ) {
        return new UpdateInterventionStatusUseCase(interventionRepositoryPort);
    }

    @Bean
    public PayInterventionUseCase payInterventionUseCase(
            InterventionRepositoryPort interventionRepositoryPort,
            PaymentRepositoryPort paymentRepositoryPort,
            PaymentPort paymentPort,
            NotificationPort notificationPort
    ) {
        return new PayInterventionUseCase(
                interventionRepositoryPort,
                paymentRepositoryPort,
                paymentPort,
                notificationPort
        );
    }

}


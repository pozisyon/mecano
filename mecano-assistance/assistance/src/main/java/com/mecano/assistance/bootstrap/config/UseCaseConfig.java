package com.mecano.assistance.bootstrap.config;

import com.mecano.assistance.application.usecase.*;
import com.mecano.assistance.domain.port.*;
import com.mecano.assistance.domain.service.DispatchDomainService;
import com.mecano.assistance.domain.service.MatchingDomainService;
import com.mecano.assistance.domain.service.MechanicEligibilityService;
import com.mecano.assistance.domain.strategy.MatchingStrategy;
import com.mecano.assistance.domain.strategy.NearestMechanicStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mecano.assistance.application.usecase.GetInvoiceUseCase;
import com.mecano.assistance.application.usecase.GetInvoicesByInterventionUseCase;
import com.mecano.assistance.domain.port.InvoiceRepositoryPort;
import com.mecano.assistance.application.usecase.GetMyInterventionsUseCase;
import com.mecano.assistance.domain.port.BreakdownRepositoryPort;
import com.mecano.assistance.domain.port.InterventionRepositoryPort;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateBreakdownRequestUseCase createBreakdownRequestUseCase(
            BreakdownRepositoryPort breakdownRepositoryPort, VehicleRepositoryPort vehicleRepositoryPort
    ) {
        return new CreateBreakdownRequestUseCase(breakdownRepositoryPort, vehicleRepositoryPort);
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
            NotificationPort notificationPort,
            DispatchDomainService dispatchDomainService,
            DispatchOfferRepositoryPort dispatchOfferRepositoryPort
    ) {
        return new FindMatchingMechanicsUseCase(
                breakdownRepositoryPort,
                mechanicRepositoryPort,
                matchingDomainService,
                notificationPort,
                dispatchDomainService,
                dispatchOfferRepositoryPort
        );
    }

    @Bean
    public AcceptInterventionUseCase acceptInterventionUseCase(
            BreakdownRepositoryPort breakdownRepositoryPort,
            MechanicRepositoryPort mechanicRepositoryPort,
            InterventionRepositoryPort interventionRepositoryPort,
            NotificationPort notificationPort,
            MechanicEligibilityService eligibilityService,
            DispatchOfferRepositoryPort dispatchOfferRepository,
            @Qualifier("webSocketRealtimeNotificationAdapter")RealtimeNotificationPort realtimeNotificationPort
    ) {
        return new AcceptInterventionUseCase(
                breakdownRepositoryPort,
                mechanicRepositoryPort,
                interventionRepositoryPort,
                notificationPort,
                eligibilityService,
                dispatchOfferRepository,
                realtimeNotificationPort
        );
    }

    @Bean
    public UpdateInterventionStatusUseCase updateInterventionStatusUseCase(
            InterventionRepositoryPort interventionRepositoryPort, @Qualifier("webSocketRealtimeNotificationAdapter")RealtimeNotificationPort real
    ) {
        return new UpdateInterventionStatusUseCase(interventionRepositoryPort, real);
    }

    @Bean
    public PayInterventionUseCase payInterventionUseCase(
            InterventionRepositoryPort interventionRepositoryPort,
            PaymentRepositoryPort paymentRepositoryPort,
            PaymentPort paymentPort,
            NotificationPort notificationPort,
            GenerateInvoiceUseCase generateInvoiceUseCase
    ) {
        return new PayInterventionUseCase(
                interventionRepositoryPort,
                paymentRepositoryPort,
                paymentPort,
                notificationPort,
                generateInvoiceUseCase
        );
    }


    @Bean
    public CreateVehicleUseCase createVehicleUseCase(
            VehicleRepositoryPort vehicleRepositoryPort
    ) {
        return new CreateVehicleUseCase(vehicleRepositoryPort);
    }

    @Bean
    public GetMyVehiclesUseCase getMyVehiclesUseCase(
            VehicleRepositoryPort vehicleRepositoryPort
    ) {
        return new GetMyVehiclesUseCase(vehicleRepositoryPort);
    }

    @Bean
    public CreateMechanicProfileUseCase createMechanicProfileUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepositoryPort
    ) {
        return new CreateMechanicProfileUseCase(mechanicProfileRepositoryPort);
    }

    @Bean
    public GetMyMechanicProfileUseCase getMyMechanicProfileUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepositoryPort
    ) {
        return new GetMyMechanicProfileUseCase(mechanicProfileRepositoryPort);
    }

    @Bean
    public UpdateMechanicAvailabilityUseCase updateMechanicAvailabilityUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepositoryPort
    ) {
        return new UpdateMechanicAvailabilityUseCase(mechanicProfileRepositoryPort);
    }

    @Bean
    public UpdateMechanicLocationUseCase updateMechanicLocationUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepositoryPort
    ) {
        return new UpdateMechanicLocationUseCase(mechanicProfileRepositoryPort);
    }

    @Bean
    public ApproveMechanicProfileUseCase approveMechanicProfileUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepositoryPort
    ) {
        return new ApproveMechanicProfileUseCase(mechanicProfileRepositoryPort);
    }

    @Bean
    public GetPendingMechanicProfilesUseCase getPendingMechanicProfilesUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepositoryPort
    ) {
        return new GetPendingMechanicProfilesUseCase(mechanicProfileRepositoryPort);
    }

    @Bean
    public MechanicEligibilityService mechanicEligibilityService(
            GeoDistancePort geoDistancePort
    ) {
        return new MechanicEligibilityService(geoDistancePort);
    }
    @Bean
    public DispatchDomainService dispatchDomainService() {
        return new DispatchDomainService();
    }
    @Bean
    public GetMyDispatchOffersUseCase getMyDispatchOffersUseCase(
            DispatchOfferRepositoryPort dispatchOfferRepositoryPort
    ) {
        return new GetMyDispatchOffersUseCase(dispatchOfferRepositoryPort);
    }

    @Bean
    public AcceptDispatchOfferUseCase acceptDispatchOfferUseCase(
            DispatchOfferRepositoryPort dispatchOfferRepositoryPort,
            BreakdownRepositoryPort breakdownRepositoryPort,
            MechanicRepositoryPort mechanicRepositoryPort,
            InterventionRepositoryPort interventionRepositoryPort,
            NotificationPort notificationPort,
            MechanicEligibilityService mechanicEligibilityService
    ) {
        return new AcceptDispatchOfferUseCase(
                dispatchOfferRepositoryPort,
                breakdownRepositoryPort,
                mechanicRepositoryPort,
                interventionRepositoryPort,
                notificationPort,
                mechanicEligibilityService
        );
    }
    @Bean
    public RejectDispatchOfferUseCase rejectDispatchOfferUseCase(
            DispatchOfferRepositoryPort dispatchOfferRepositoryPort
    ) {
        return new RejectDispatchOfferUseCase(dispatchOfferRepositoryPort);
    }

    @Bean
    public RedispatchPendingBreakdownsUseCase redispatchPendingBreakdownsUseCase(
            BreakdownRepositoryPort breakdownRepositoryPort,
            MechanicRepositoryPort mechanicRepositoryPort,
            DispatchOfferRepositoryPort dispatchOfferRepositoryPort,
            MatchingDomainService matchingDomainService,
            DispatchDomainService dispatchDomainService,
            NotificationPort notificationPort
    ) {
        return new RedispatchPendingBreakdownsUseCase(
                breakdownRepositoryPort,
                mechanicRepositoryPort,
                dispatchOfferRepositoryPort,
                matchingDomainService,
                dispatchDomainService,
                notificationPort
        );
    }

    @Bean
    public GenerateInvoiceUseCase generateInvoiceUseCase(
            InvoiceRepositoryPort invoiceRepositoryPort
    ) {
        return new GenerateInvoiceUseCase(invoiceRepositoryPort);
    }

    @Bean
    public GetInvoiceUseCase getInvoiceUseCase(
            InvoiceRepositoryPort invoiceRepositoryPort
    ) {
        return new GetInvoiceUseCase(invoiceRepositoryPort);
    }

    @Bean
    public GetInvoicesByInterventionUseCase getInvoicesByInterventionUseCase(
            InvoiceRepositoryPort invoiceRepositoryPort
    ) {
        return new GetInvoicesByInterventionUseCase(invoiceRepositoryPort);
    }

    @Bean
    public RejectMechanicUseCase rejectMechanicUseCase(
            MechanicRepositoryPort mechanicRepositoryPort
    ) {
        return new RejectMechanicUseCase(mechanicRepositoryPort);
    }

    @Bean
    public ApproveMechanicUseCase approveMechanicUseCase(
            MechanicRepositoryPort mechanicRepositoryPort
    ) {
        return new ApproveMechanicUseCase(mechanicRepositoryPort);
    }

    @Bean
    public GetPendingMechanicsUseCase getPendingMechanicsUseCase(
            MechanicRepositoryPort mechanicRepositoryPort
    ) {
        return new GetPendingMechanicsUseCase(mechanicRepositoryPort);
    }

    @Bean
    public GetMyBreakdownsUseCase getMyBreakdownsUseCase(
            BreakdownRepositoryPort breakdownRepositoryPort
    ) {
        return new GetMyBreakdownsUseCase(breakdownRepositoryPort);
    }
    @Bean
    public GetMyInterventionsUseCase getMyInterventionsUseCase(
            BreakdownRepositoryPort breakdownRepositoryPort,
            InterventionRepositoryPort interventionRepositoryPort
    ) {
        return new GetMyInterventionsUseCase(
                breakdownRepositoryPort,
                interventionRepositoryPort
        );
    }

    @Bean
    public GetMyPaymentsUseCase getMyPaymentsUseCase(
            GetMyInterventionsUseCase getMyInterventionsUseCase,
            PaymentRepositoryPort paymentRepositoryPort
    ) {
        return new GetMyPaymentsUseCase(
                getMyInterventionsUseCase,
                paymentRepositoryPort
        );
    }

    @Bean
    public GetMyInvoicesUseCase getMyInvoicesUseCase(
            GetMyInterventionsUseCase getMyInterventionsUseCase,
            InvoiceRepositoryPort invoiceRepositoryPort
    ) {
        return new GetMyInvoicesUseCase(
                getMyInterventionsUseCase,
                invoiceRepositoryPort
        );
    }
    @Bean
    public GetMechanicInterventionHistoryUseCase
    getMechanicInterventionHistoryUseCase(
            InterventionRepositoryPort interventionRepositoryPort
    ) {
        return new GetMechanicInterventionHistoryUseCase(
                interventionRepositoryPort
        );
    }

}


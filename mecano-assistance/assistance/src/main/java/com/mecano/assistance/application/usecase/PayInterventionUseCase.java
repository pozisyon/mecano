package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.PayInterventionCommand;
import com.mecano.assistance.application.result.PayInterventionResult;
import com.mecano.assistance.domain.model.Payment;
import com.mecano.assistance.domain.model.PaymentStatus;
import com.mecano.assistance.domain.port.InterventionRepositoryPort;
import com.mecano.assistance.domain.port.NotificationPort;
import com.mecano.assistance.domain.port.PaymentPort;
import com.mecano.assistance.domain.port.PaymentRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

public class PayInterventionUseCase {

    private final InterventionRepositoryPort interventionRepository;
    private final PaymentRepositoryPort paymentRepository;
    private final PaymentPort paymentPort;
    private final NotificationPort notificationPort;
    private final GenerateInvoiceUseCase generateInvoiceUseCase;
    public PayInterventionUseCase(
            InterventionRepositoryPort interventionRepository,
            PaymentRepositoryPort paymentRepository,
            PaymentPort paymentPort,
            NotificationPort notificationPort,
            GenerateInvoiceUseCase generateInvoiceUseCase
    ) {
        this.interventionRepository = interventionRepository;
        this.paymentRepository = paymentRepository;
        this.paymentPort = paymentPort;
        this.notificationPort = notificationPort;
        this.generateInvoiceUseCase = generateInvoiceUseCase;
    }

    public PayInterventionResult execute(PayInterventionCommand command) {
        var intervention = interventionRepository.findById(command.interventionId())
                .orElseThrow(() -> new NotFoundException("Intervention not found"));//new IllegalArgumentException("Intervention not found"));

        if (paymentRepository.findByInterventionId(command.interventionId()).isPresent()) {
            throw new BusinessException("Intervention already has a payment");
        }

        var payment = Payment.create(
                command.interventionId(),
                command.amount(),
                command.method()
        );

        boolean charged = paymentPort.charge(command.amount(), command.method());

        if (charged) {
            payment.confirm();
            intervention.markAsPaid();
            interventionRepository.save(intervention);
        } else {
            payment.fail();
        }

        var saved = paymentRepository.save(payment);
        generateInvoiceUseCase.execute(
                saved.getInterventionId(),
                saved.getId(),
                saved.getAmount().amount(),
                saved.getAmount().currency()
        );
        if (saved.getStatus() == PaymentStatus.PAID) {
            notificationPort.notifyDriverPaymentConfirmed(saved);
        }

        return new PayInterventionResult(
                saved.getId(),
                saved.getInterventionId(),
                saved.getStatus()
        );
    }
}
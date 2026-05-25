package com.mecano.assistance.infrastructure.notification;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.model.Payment;
import com.mecano.assistance.domain.port.NotificationPort;
import org.springframework.stereotype.Component;

@Component
public class FakeNotificationAdapter implements NotificationPort {

    @Override
    public void notifyMechanic(Mechanic mechanic, BreakdownRequest request) {
        System.out.println("[NOTIFICATION] Mechanic " + mechanic.getFullName()
                + " notified for breakdown " + request.getId());
    }

    @Override
    public void notifyDriverInterventionAccepted(Intervention intervention) {
        System.out.println("[NOTIFICATION] Driver notified: intervention accepted "
                + intervention.getId());
    }

    @Override
    public void notifyDriverPaymentConfirmed(Payment payment) {
        System.out.println("[NOTIFICATION] Driver notified: payment confirmed "
                + payment.getId());
    }
}
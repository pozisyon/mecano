package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.model.Payment;

public interface NotificationPort {

    void notifyMechanic(Mechanic mechanic, BreakdownRequest request);

    void notifyDriverInterventionAccepted(Intervention intervention);

    void notifyDriverPaymentConfirmed(Payment payment);
}
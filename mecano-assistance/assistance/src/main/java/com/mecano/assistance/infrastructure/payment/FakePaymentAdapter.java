package com.mecano.assistance.infrastructure.payment;

import com.mecano.assistance.domain.port.PaymentPort;
import com.mecano.assistance.domain.valueobject.Money;
import org.springframework.stereotype.Component;

@Component
public class FakePaymentAdapter implements PaymentPort {

    @Override
    public boolean charge(Money amount, String method) {
        return amount.amount().signum() > 0 && method != null && !method.isBlank();
    }
}
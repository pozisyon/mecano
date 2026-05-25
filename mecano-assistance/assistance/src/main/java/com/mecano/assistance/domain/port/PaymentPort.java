package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.valueobject.Money;

public interface PaymentPort {

    boolean charge(Money amount, String method);
}
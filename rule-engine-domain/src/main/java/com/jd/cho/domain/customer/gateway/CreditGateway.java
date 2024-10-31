package com.jd.cho.domain.customer.gateway;

import com.jd.cho.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}

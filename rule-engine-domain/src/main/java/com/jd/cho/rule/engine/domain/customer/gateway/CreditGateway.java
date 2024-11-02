package com.jd.cho.rule.engine.infr.domain.customer.gateway;

import com.jd.cho.rule.engine.infr.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}

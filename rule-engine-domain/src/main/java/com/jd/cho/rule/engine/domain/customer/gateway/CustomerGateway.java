package com.jd.cho.rule.engine.infr.domain.customer.gateway;

import com.jd.cho.rule.engine.infr.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}

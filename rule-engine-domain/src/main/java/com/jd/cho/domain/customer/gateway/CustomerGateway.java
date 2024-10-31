package com.jd.cho.domain.customer.gateway;

import com.jd.cho.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}

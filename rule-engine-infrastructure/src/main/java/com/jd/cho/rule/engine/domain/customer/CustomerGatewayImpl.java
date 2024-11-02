package com.jd.cho.rule.engine.domain.customer;

import com.jd.cho.rule.engine.infr.domain.customer.Customer;
import com.jd.cho.rule.engine.infr.domain.customer.gateway.CustomerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerGatewayImpl implements CustomerGateway {
    @Autowired
    private CustomerMapper customerMapper;

    public Customer getByById(String customerId){
      CustomerDO customerDO = customerMapper.getById(customerId);
      //Convert to Customer
      return null;
    }
}

package com.jd.cho.rule.engine.app.customer;

import com.jd.cho.rule.engine.app.customer.executor.CustomerAddCmdExe;
import com.jd.cho.rule.engine.app.customer.executor.query.CustomerListByNameQryExe;
import com.jd.cho.rule.engine.client.api.CustomerServiceI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private CustomerAddCmdExe customerAddCmdExe;

    @Resource
    private CustomerListByNameQryExe customerListByNameQryExe;


//    @Override
//    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
//        return customerListByNameQryExe.execute(customerListByNameQry);
//    }

}
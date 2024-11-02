package com.jd.cho.customer;

import com.jd.cho.api.CustomerServiceI;
import com.jd.cho.customer.executor.CustomerAddCmdExe;
import com.jd.cho.customer.executor.query.CustomerListByNameQryExe;
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
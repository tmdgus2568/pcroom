package com.hsh.pcroom_customer.chargemachine;

import com.hsh.pcroom_customer.*;

import java.util.List;

public class ChargeMachineService {
    RateplanDAO rateplanDAO = new RateplanDAO();
    CustomerDAO customerDAO = new CustomerDAO();

    public List<RateplanVO> selectRateplanByRole(String role){
        return rateplanDAO.selectRateplanByRole(role);
    }

    public int updateCustomerForBuyTime(String customerId, int apply_time, String rateplanId){
        return customerDAO.updateCustomerForBuyTime(customerId, apply_time, rateplanId);
    }

    public CustomerVO selectCustomerById(String id){
        return customerDAO.selectCustomerById(id);
    }

}

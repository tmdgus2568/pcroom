package com.hsh.pcroom_customer;

public class UserComputerService {
    CustomerDAO dao = new CustomerDAO();
    public boolean insertCustomer(CustomerVO customer){
        return dao.insertCustomerDAO(customer);
    }

    public CustomerVO selectByIdPassword(String id, String pw){
        return dao.selectByIdPassword(id, pw);
    }
}

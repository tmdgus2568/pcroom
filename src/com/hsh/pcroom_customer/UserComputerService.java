package com.hsh.pcroom_customer;

import java.util.List;

public class UserComputerService {
    CustomerDAO customerDao = new CustomerDAO();
    SeatDAO seatDAO = new SeatDAO();
    public boolean insertCustomer(CustomerVO customer){
        return customerDao.insertCustomerDAO(customer);
    }

    public CustomerVO selectByIdPassword(String id, String pw){
        return customerDao.selectByIdPassword(id, pw);
    }

    public List<SeatVO> selectSeatAll(){
        return seatDAO.selectSeatAll();
    }

    public int updateSeat(int id, String is_usable){
        return seatDAO.updateSeat(id, is_usable);
    }
}

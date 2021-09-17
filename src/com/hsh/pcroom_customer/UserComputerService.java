package com.hsh.pcroom_customer;

import java.util.List;

public class UserComputerService {
    CustomerDAO customerDAO = new CustomerDAO();
    SeatDAO seatDAO = new SeatDAO();
    VisitDAO visitDAO = new VisitDAO();
    public boolean insertCustomer(CustomerVO customer){
        return customerDAO.insertCustomerDAO(customer);
    }

    public CustomerVO selectByIdPassword(String id, String pw){
        return customerDAO.selectByIdPassword(id, pw);
    }

    public List<SeatVO> selectSeatAll(){
        return seatDAO.selectSeatAll();
    }

    public int updateSeat(int id, String is_usable){
        return seatDAO.updateSeat(id, is_usable);
    }

    public int updateCustomerRemainTime(CustomerVO customer){
        return customerDAO.updateCustomerRemainTime(customer);
    }

    public int insertVisit(VisitVO visit){
        return visitDAO.insertVisit(visit);
    }


}

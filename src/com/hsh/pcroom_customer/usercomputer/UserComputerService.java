package com.hsh.pcroom_customer.usercomputer;

import com.hsh.pcroom_customer.*;

import java.util.List;

public class UserComputerService {
    CustomerDAO customerDAO = new CustomerDAO();
    SeatDAO seatDAO = new SeatDAO();
    VisitDAO visitDAO = new VisitDAO();
    PorderDAO porderDAO = new PorderDAO();
    ProductDAO productDAO = new ProductDAO();

    public boolean insertCustomer(CustomerVO customer){
        return customerDAO.insertCustomer(customer);
    }

    public CustomerVO selectByIdPassword(String id, String pw){
        return customerDAO.selectByIdPassword(id, pw);
    }

    public List<SeatVO> selectSeatAll(){
        return seatDAO.selectSeatAll();
    }

    public SeatVO selectSeatById(int id){
        return seatDAO.selectSeatById(id);
    }

    public int updateSeat(int id, String is_usable, String customer_id){
        return seatDAO.updateSeat(id, is_usable, customer_id);
    }

    public int updateCustomerRemainTime(CustomerVO customer){
        return customerDAO.updateCustomerRemainTime(customer);
    }

    public int insertVisit(VisitVO visit){
        return visitDAO.insertVisit(visit);
    }

    public int insertPorder(PorderVO porder, List<PorderdetailVO> porderdetails){
        return porderDAO.insertPorder(porder, porderdetails);
    }

    public List<ProductVO> selectProductByKinds(String kinds){
        return productDAO.selectProductByKinds(kinds);
    }

    public CustomerVO selectCustomerById(String id){
        return customerDAO.selectCustomerById(id);
    }



}

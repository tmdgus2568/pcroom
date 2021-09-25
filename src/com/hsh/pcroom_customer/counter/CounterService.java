package com.hsh.pcroom_customer.counter;

import com.hsh.pcroom_customer.PorderDAO;
import com.hsh.pcroom_customer.PorderVO;
import com.hsh.pcroom_customer.SeatDAO;
import com.hsh.pcroom_customer.SeatVO;

import java.util.List;

public class CounterService {
    SeatDAO seatDAO = new SeatDAO();
    PorderDAO porderDAO = new PorderDAO();
    public List<SeatVO> selectSeatAll(){
        return seatDAO.selectSeatAll();
    }
    public List<PorderVO> selectPorderAllByStatus(String status){
        return porderDAO.selectPorderAllByStatus(status);
    }
    public boolean deletePorderById(int id){
        return porderDAO.deletePorderById(id);
    }
}

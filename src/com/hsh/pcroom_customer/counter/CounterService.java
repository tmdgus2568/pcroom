package com.hsh.pcroom_customer.counter;

import com.hsh.pcroom_customer.*;

import java.util.List;

public class CounterService {
    SeatDAO seatDAO = new SeatDAO();
    PorderDAO porderDAO = new PorderDAO();
    VisitDAO visitDAO = new VisitDAO();
    public List<SeatVO> selectSeatAll(){
        return seatDAO.selectSeatAll();
    }
    public List<Checkporder> selectPorderAllByStatus(String status){
        return porderDAO.selectPorderAllByStatus(status);
    }
    public boolean updatePorderById(int id){
        return porderDAO.updatePorderById(id);
    }
    public List<VisitVO> selectVisitAll(){
        return visitDAO.selectVisitAll();
    }
}

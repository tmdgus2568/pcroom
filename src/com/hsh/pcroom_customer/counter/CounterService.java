package com.hsh.pcroom_customer.counter;

import com.hsh.pcroom_customer.*;

import java.util.List;

public class CounterService {
    SeatDAO seatDAO = new SeatDAO();
    PorderDAO porderDAO = new PorderDAO();
    VisitDAO visitDAO = new VisitDAO();
    CheckporderDAO checkporderDAO = new CheckporderDAO();
    public List<SeatVO> selectSeatAll(){
        return seatDAO.selectSeatAll();
    }
    public List<CheckporderVO> selectCheckporderAllByStatus(String status){
        return checkporderDAO.selectCheckporderAllByStatus(status);
    }
    public int updatePorderById(int id){
        return porderDAO.updatePorderById(id);
    }
    public List<VisitVO> selectVisitAll(){
        return visitDAO.selectVisitAll();
    }
}

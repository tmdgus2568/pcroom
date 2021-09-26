package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitDAO {
    public List<VisitVO> selectVisitAll(){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        String sql = "select * from visit order by id";
        ResultSet rs = null;
        List<VisitVO> visits = new ArrayList<>();

        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                visits.add(makeVisit(rs));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return visits;
    }
    public int insertVisit(VisitVO visit){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        String sql = "";

        if(visit.getVisit_date() == null){
            sql = "insert into visit(customer_id, seat_id, exit_date) " +
                    "values(?,?,?)";
        }
        else if(visit.getExit_date() == null){
            sql = "insert into visit(customer_id, seat_id, visit_date) " +
                    "values(?,?,?)";
        }
        int result = 0;

        try{
            st = conn.prepareStatement(sql);
            st.setString(1, visit.getCustomer_id());
            st.setInt(2, visit.getSeat_id());
            if(visit.getVisit_date() == null){
                st.setDate(3, visit.getExit_date());
            }
            else if(visit.getExit_date() == null){
                st.setDate(3, visit.getVisit_date());
            }

            result = st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.dbClose(conn, st, null);
        }

        return result;
    }

    private VisitVO makeVisit(ResultSet rs) throws SQLException {
        VisitVO visit = new VisitVO();
        visit.setId(rs.getInt("id"));
        visit.setCustomer_id(rs.getString("customer_id"));
        visit.setVisit_date(rs.getDate("visit_date"));
        visit.setExit_date(rs.getDate("exit_date"));
        visit.setSeat_id(rs.getInt("seat_id"));
        return visit;

    }
}

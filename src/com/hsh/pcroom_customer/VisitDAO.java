package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisitDAO {
    public int insertVisit(VisitVO visit){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        String sql = "";

        if(visit.getVisit_date() == null){
            sql = "insert into visit(customer_id, seat_id, exit_date) " +
                    "values(?,?,?)";
            System.out.println(visit.getExit_date());
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
}

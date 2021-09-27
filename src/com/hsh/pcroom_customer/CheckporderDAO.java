package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckporderDAO {
    public List<CheckporderVO> selectCheckporderAllByStatus(String status){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        String sql = "select PORDER.*, PORDER_DETAIL.* from porder " +
                "inner join PORDER_DETAIL on PORDER.id=PORDER_DETAIL.porder_id " +
                "where PORDER.payment_status=? order by PORDER.id";
        ResultSet rs = null;
        List<CheckporderVO> checkporders = new ArrayList<>();
        try {
            st = conn.prepareStatement(sql);
            st.setString(1,status);
            rs = st.executeQuery();
            while (rs.next()){
                checkporders.add(makeCheckporder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return checkporders;

    }

    public CheckporderVO makeCheckporder(ResultSet rs) throws SQLException {
        CheckporderVO checkporder = new CheckporderVO();

        checkporder.setPorder_id(rs.getInt("id"));
        checkporder.setCustomer_id(rs.getString("customer_id"));
        checkporder.setPayment_way(rs.getString("payment_way"));
        checkporder.setRequest(rs.getString("request"));
        checkporder.setPayment_date(rs.getDate("payment_date"));
        checkporder.setSeat_id(rs.getInt("seat_id"));
        checkporder.setPrice_sum(rs.getInt("price_sum"));
        checkporder.setProduct_id(rs.getInt("product_id"));
        checkporder.setNum(rs.getInt("num"));
        checkporder.setPrice(rs.getInt("price"));
        checkporder.setName(rs.getString("name"));

        return checkporder;
    }

}

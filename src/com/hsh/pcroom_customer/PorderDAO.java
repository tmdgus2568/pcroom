package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import java.sql.*;
import java.util.List;

public class PorderDAO {

    // 주문사항을 저장한다.
    public int insertPorder(List<PorderVO> porders){
        int result = 0;
        String sql = "insert into porder(product_id, customer_id, payment_way, request, payment_date, seat_id)" +
                " values(?,?,?,?,?,?)";
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        Date current = new Date(System.currentTimeMillis());

        try{
            conn.setAutoCommit(false);
            st = conn.prepareStatement(sql);

            for(PorderVO porder : porders){
                st.setInt(1,porder.getProduct_id());
                st.setString(2,porder.getCustomer_id());
                st.setString(3, porder.getPayment_way());
                st.setString(4,porder.getRequest());
                st.setDate(5,current);
                st.setInt(6,porder.getSeat_id());

                result += st.executeUpdate();
            }

            if(result == porders.size()){
                conn.commit();
                return result;
            }

            throw new SQLException();

        } catch (SQLException e) {
            try {
                e.printStackTrace();
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }finally {
            DBUtil.dbClose(conn, st, null);
        }
        return 0;
    }

    public PorderVO makePorder(ResultSet rs) throws SQLException {
        PorderVO porder = new PorderVO();
        porder.setId(rs.getInt("id"));
        porder.setId(rs.getInt("prodcut_id"));
        porder.setCustomer_id(rs.getString("customer_id"));
        porder.setPayment_way(rs.getString("payment_way"));
        porder.setPayment_status(rs.getString("payment_status"));
        porder.setRequest(rs.getString("request"));
        porder.setPayment_date(rs.getDate("payment_date"));

        return porder;

    }
}

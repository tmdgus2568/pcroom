package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PorderDAO {

//    p_customerid in varchar2,
//    p_productid in int,
//    p_paymentway in varchar2,
//    p_request in varchar2,
//    p_paymentdate in date,
//    p_seatid in int,
//    p_num in int

    // 주문사항을 저장한다.
    public int insertPorder(PorderVO porder, List<PorderdetailVO> porderdeatails){
        int result = 0;
        String porder_sql = "insert into porder(customer_id, payment_way, request, payment_date, seat_id, price_sum)" +
                " values(?,?,?,?,?,?)";
        String searchid_sql = "select id from porder where customer_id=? and payment_date=? and seat_id=?";
        String porder_detail_sql = "insert into porder_detail(porder_id, product_id, num, price,name) values(?,?,?,?,?)";
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        Date current = new Date(System.currentTimeMillis());
        int porder_id = 0;
        ResultSet rs = null;

        try{
            conn.setAutoCommit(false);
            st = conn.prepareStatement(porder_sql);

            st.setString(1,porder.getCustomer_id());
            st.setString(2, porder.getPayment_way());
            st.setString(3,porder.getRequest());
            st.setDate(4,current);
            st.setInt(5,porder.getSeat_id());
            st.setInt(6,porder.getPrice_sum());

            // porder 저장
            if(st.executeUpdate() == 1){
                st = conn.prepareStatement(searchid_sql);

                st.setString(1,porder.getCustomer_id());
                st.setDate(2,current);
                st.setInt(3,porder.getSeat_id());

                rs = st.executeQuery();
                if(rs.next()){
                    // 저장한 porder의 Id를 가져온다
                    porder_id = rs.getInt("id");
                }

                st = conn.prepareStatement(porder_detail_sql);
                for(int i=0;i<porderdeatails.size();i++){
                    PorderdetailVO porderdetail = porderdeatails.get(i);
                    st.setInt(1,porder_id);
                    st.setInt(2, porderdetail.getProduct_id());
                    st.setInt(3, porderdetail.getNum());
                    st.setInt(4, porderdetail.getPrice());
                    st.setString(5,porderdetail.getName());

                    result += st.executeUpdate();
                }
                if(result == porderdeatails.size()){
                    conn.commit();
                    return result;
                }

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

    public List<CheckporderVO> selectPorderAllByStatus(String status){
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

    public boolean updatePorderById(int id){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        String sql = "update porder set payment_status=? where id=?";
        int result = 0;
        try{
            conn.setAutoCommit(false);
            st = conn.prepareStatement(sql);
            st.setString(1,"Y");
            st.setInt(2,id);

            result = st.executeUpdate();

            if(result!=1){
                conn.rollback();
                return false;
            }
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.dbClose(conn, st, null);
        }
        return true;
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

    public PorderVO makePorder(ResultSet rs) throws SQLException {
        PorderVO porder = new PorderVO();


        porder.setId(rs.getInt("id"));
        porder.setCustomer_id(rs.getString("customer_id"));
        porder.setPayment_way(rs.getString("payment_way"));
        porder.setPayment_status(rs.getString("payment_status"));
        porder.setRequest(rs.getString("request"));
        porder.setPayment_date(rs.getDate("payment_date"));
        porder.setProduct_name(rs.getString("product_name"));


        return porder;

    }
}

package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.*;

// String id, String password, String phone, String name, Date birthday, String address
public class CustomerDAO {

    public boolean insertCustomer(CustomerVO customer){
        boolean result = false;
        String sql = "INSERT into CUSTOMER(id,password,phone,name,birthday,address,join_date,remain_time,role) " +
                        "values(?,?,?,?,?,?,?,?,?)";
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,customer.getId());
            st.setString(2,customer.getPassword());
            st.setString(3,customer.getPhone());
            st.setString(4,customer.getName());
            st.setDate(5,customer.getBirthday());
            st.setString(6,customer.getAddress());
            st.setDate(7, new Date(System.currentTimeMillis()));
            st.setInt(8,  0);
            st.setString(9,customer.getRole());

            result = st.execute();


        }catch (SQLException e){
            System.out.println("데이터베이스 오류가 발생하였습니다.");
        }finally {
            DBUtil.dbClose(conn, st, null);
        }
        return result;

    }


    // 로그인을 위해, 아이디와 비밀번호로 찾기
    public CustomerVO selectByIdPassword(String id, String pw){
        CustomerVO result = null;
        String sql = "select * from CUSTOMER " +
                "where id=? and password=?";
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, pw);

            rs = st.executeQuery();

            if(rs.next()){
                result = makeCustomer(rs);
            }


        } catch (SQLException e) {
            System.out.println("데이터베이스 오류가 발생하였습니다.");
        }finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return result;
    }

    // 요금 결제 시, update -> 결제 관련이므로 프로지서(트랜잭션)처리
    public int updateCustomerForBuyTime(String customerId, int apply_time, String rateplanId) {
        Connection conn = DBUtil.dbConnect();
        CallableStatement st = null;
        int result = 0;
        try{
            st = conn.prepareCall("{call buy_rateplan_proc(?,?,?)}");
            st.setString(1,customerId);
            st.setInt(2,apply_time);
            st.setString(3,rateplanId);

            result = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류가 발생하였습니다.");
        }finally {
            DBUtil.dbClose(conn, st,null);
        }
        return result;
    }

    public int updateCustomerRemainTime(CustomerVO customer){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        String sql = "update customer set remain_time=? where id=?";
        int result = 0;

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, customer.getRemain_time());
            st.setString(2, customer.getId());
            result = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류가 발생하였습니다.");
        }finally {
            DBUtil.dbClose(conn, st, null);
        }
        return result;
    }

    public CustomerVO selectCustomerById(String id){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        String sql = "select * from customer where id=?";
        CustomerVO result = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(sql);
            st.setString(1,id);
            rs = st.executeQuery();

            if(rs.next()){
                result = makeCustomer(rs);
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류가 발생하였습니다.");
        }finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return result;
    }


    private CustomerVO makeCustomer(ResultSet rs) throws SQLException{
        CustomerVO customer = new CustomerVO();

        customer.setId(rs.getString("id"));
        customer.setName(rs.getString("name"));
        customer.setRemain_time(rs.getInt("remain_time"));
        customer.setRole(rs.getString("role"));

        return customer;
    }


}

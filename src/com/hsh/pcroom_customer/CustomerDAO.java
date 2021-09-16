package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.*;

// String id, String password, String phone, String name, Date birthday, String address
public class CustomerDAO {

    public boolean insertCustomerDAO(CustomerVO customer){
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

    private CustomerVO makeCustomer(ResultSet rs) throws SQLException{
        CustomerVO customer = new CustomerVO();

        customer.setId(rs.getString("id"));
        customer.setName(rs.getString("name"));
        customer.setRemain_time(rs.getInt("remain_time"));
        customer.setRole(rs.getString("role"));

        return customer;
    }


}

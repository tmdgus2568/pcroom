package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// String id,  int apply_time, int price, String role
public class RateplanDAO {

    // 회원에 따른 요금제 가져오기
    public List<RateplanVO> selectRateplanByRole(String role){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        String sql = "select id, apply_time, price from RATEPLAN " +
                "where role=?";
        List<RateplanVO> result = new ArrayList<>();

        ResultSet rs = null;

        try{
            st = conn.prepareStatement(sql);
            st.setString(1,role);
            rs = st.executeQuery();

            while (rs.next()){
                result.add(makeRateplan(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public RateplanVO makeRateplan(ResultSet rs) throws SQLException {
        RateplanVO rateplan = new RateplanVO();
        rateplan.setId(rs.getString("id"));
        rateplan.setApply_time(rs.getInt("apply_time"));
        rateplan.setPrice(rs.getInt("price"));

        return rateplan;

    }
}

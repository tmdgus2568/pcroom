package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// int id, char is_usable
public class SeatDAO {
    public List<SeatVO> selectSeatAll(){
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<SeatVO> result = new ArrayList<>();
        String sql = "select * from SEAT";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                result.add(makeSeat(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return result;

    }

    public int updateSeat(int id, String is_usable){
        Connection conn = DBUtil.dbConnect();
        int result = 0;
        String sql = "update SEAT set " +
                "id=?, " +
                "is_usable=? " +
                "where id=?";
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(sql);
            st.setInt(1,id);
            st.setString(2,is_usable);
            st.setInt(3, id);
            result = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.dbClose(conn, st, null);
        }
        return result;
    }

    public SeatVO makeSeat(ResultSet rs) throws SQLException{
        SeatVO seat = new SeatVO();
        seat.setId(rs.getInt("id"));
        seat.setIs_usable(rs.getString("is_usable"));
        return seat;
    }
}

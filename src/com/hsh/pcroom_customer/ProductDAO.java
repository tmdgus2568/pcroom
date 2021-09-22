package com.hsh.pcroom_customer;

import com.hsh.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<ProductVO> selectProductByKinds(String kinds){
        String sql = "select * from product where kinds=?";
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProductVO> products = new ArrayList<>();

        try{
            st = conn.prepareStatement(sql);
            st.setString(1,kinds);

            rs = st.executeQuery();
            while(rs.next()){
                products.add(makeProduct(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return products;
    }

    public ProductVO makeProduct(ResultSet rs) throws SQLException {
        ProductVO product = new ProductVO();
        product.setId(rs.getInt("id"));
        product.setPrice(rs.getInt("price"));
        product.setName(rs.getString("name"));
        product.setKinds(rs.getString("kinds"));

        return product;
    }
}

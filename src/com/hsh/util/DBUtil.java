package com.hsh.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

    public static void dbClose(Connection conn, Statement st, ResultSet rs) {
        //자원반납
        try {
            if(rs!=null) rs.close();
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection dbConnect() {
        //DB연결
        Connection conn = null;
        String url, id, pass;

        try {
            //class memory load , instance 사용하는 방법
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Properties pro = new Properties();
            pro.load(new FileReader("src/com/hsh/util/dbinfo.properties"));
            url = pro.getProperty("url");
            id = pro.getProperty("userid");
            pass = pro.getProperty("password");
            conn = DriverManager.getConnection(url, id, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

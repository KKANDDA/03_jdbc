package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public  static Connection getConnection(){

        Connection con = null;
        Properties prop = new Properties();

        try
        {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            try {
                con = DriverManager.getConnection(url,user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  static void close(ResultSet rsult){
        try {
            rsult.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

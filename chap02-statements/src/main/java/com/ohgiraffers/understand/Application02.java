package com.ohgiraffers.understand;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt =null;
        ResultSet rset = null;
        Properties prop = new Properties();

        Scanner scr = new Scanner(System.in);
        System.out.println("조회하려는 사원의 이름을 입력해 주세요 :");
        String name = scr.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            pstmt = con.prepareStatement(prop.getProperty("selectByName"));
            pstmt.setString(1, name);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1)+" "+rset.getString(2)+" "+rset.getString(3)
                +" "+rset.getString(4)+" "+rset.getString(5)+" "+rset.getString(6)
                +" "+rset.getString(7)+" "+rset.getString(8)+" "+rset.getString(9)
                +" "+rset.getString(10)+" "+rset.getString(11)+" "+rset.getString(12)
                +" "+rset.getString(13)+" "+rset.getString(14)+" "+rset.getString(15)
                +" "+rset.getString(16));

            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }


    }
}

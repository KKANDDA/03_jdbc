package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        // 결과를 담아줄 변수, 성공 여부 1 또는 0, 0이 뜨면 mysql 에서 문제가 생겼음을 유추할 수 있다.
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1,"쌀국수");
            pstmt.setInt(2,11900);
            pstmt.setInt(3,4);
            pstmt.setString(4,"Y");

            result = pstmt.executeUpdate();
            System.out.println("결과 : " + result + " 굿잡! 후훗..");


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

    }
}

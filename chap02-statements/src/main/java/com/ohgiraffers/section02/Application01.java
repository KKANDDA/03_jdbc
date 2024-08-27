package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null; // 어플리케이션 5와 달라진 점..!

        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE"); // 만들때 쿼리문을 만든다.
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2)); // 이름도 가능하고 인덱스 번호도 가능하다.
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(rset);
            close(pstmt);
        }


    }
}

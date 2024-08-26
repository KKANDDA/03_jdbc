package com.ohgiraffers.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        // 사원번호에 해당하는 사원의 정보를 조회한다.
        // 1. connection
        // 2. statement 쿼리 만들기
        // 3. 쿼리를 날린다.
        // 4. resultSet 올 받는다.

        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rsest = null;

        try {
            stmt = con.createStatement();
            String empId = "200"; // 알아서 정수도 형변환되서 정수로 들어간다.

            rsest = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID =" + empId);

                    while (rsest.next()) {
                System.out.println(rsest.getString("EMP_ID") + " " + rsest.getString("EMP_NAME"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rsest);
        }
    }
}

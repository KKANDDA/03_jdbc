package com.ohgiraffers.section03.sqlinjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.*;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

    private static String empId = "210";
    private static String empName = "'OR 1=1 AND EMP_ID = '200"; // OR로 완성된 쿼리문을 이어 붙여 원하는 정보를 뜯어내는 쿼리문을 작성했다.
    // 프리페어스테이트먼트의 미완성 쿼리는 상기 쿼리문을 문자열로 읽는다. 그러나 스테이트먼트의 완성된 쿼리는 상기의 쿼리문을 쿼리문으로 정직하게 읽는다.

    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '"+ empId+"' AND EMP_NAME = '"+empName+"'";

        System.out.println(query);

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println(rs.getString("EMP_NAME")+"님 환영합니다.");
            }else{
                System.out.println("회원정보가 존재하지 않습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rs);
        }

    }

}

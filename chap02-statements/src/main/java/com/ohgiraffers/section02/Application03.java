package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

        // 성씨를 입력 받아 해당 성을가진 사원 조회
        // SELECT EMP_ID, EMP_NAME FROME EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%');

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner src = new Scanner(System.in);
        System.out.println("사원의 성씨만으로 해당하는 사원들을 조회해 드립니다.");
        String firstName = src.nextLine();

        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%')";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, firstName);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString(1)+" "+rset.getString(2));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }
}

package com.ohgiraffers.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);

        // 이름을 입력받아서 해당 사원 아이디와 이름 조회
        // 쿼리문도 변수로 따로 만들어서 넣어주세요

        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;



        try {
            stmt = con.createStatement();
            System.out.println("찾고자 하는 사원의 이름을 입력해 주세요. 해당 사원의아이디와 이름을 알려 드립니다.");

            String empName = scr.nextLine();
            String query = ("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME =");
            rset = stmt.executeQuery(query + "'"+empName+"'");
            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }


    }
}

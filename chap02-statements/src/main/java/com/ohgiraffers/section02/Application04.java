package com.ohgiraffers.section02;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application04 {
    public static void main(String[] args) {

        // xml 파일을 이용한 조회 -- 어디서 존재하든 사용 할 수 있게 한다. 클래스에 모아둘 수도 있다.
        // xml - 특수한 목적을 가진 마크업 언어

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner src = new Scanner(System.in);
        System.out.println("성씨를 입력해 주세요 : ");
        String empFirstName = src.nextLine();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            pstmt = con.prepareStatement(prop.getProperty("selectEmpByFirstName"));
            pstmt.setString(1, empFirstName);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1)+" "+rset.getString(2)+" "+rset.getString(3));
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

package com.ohgiraffers.section01.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Appication {
    public static void main(String[] args) {

        //

        Connection con = getConnection(); // 데이터 베이스와 연결을 도와주는 클래스
        Properties prop = new Properties(); // db의 주소와 사용할 아이디와 비밀번호를 받아낼 클래스

        PreparedStatement pstmt = null; // mysql 문법을 담은 클래스
        ResultSet rset = null; // 문법대로 db를 조회한 결과값




    }
}

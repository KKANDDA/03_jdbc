package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {
    /*
    * 사용자가 원하는 메뉴를 등록할 수 있도록 만들어 주세요.
    * 등록이 완료되면 성공, 실패하면 실패라고 출력해 주세요.
    * */

    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;

        System.out.println("메뉴 등록 서비스입니다.");
        Scanner scr = new Scanner(System.in);

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));

            System.out.println("음식의 이름을 등록해 주십시오 : ");
            String foodName = scr.nextLine();
            pstmt.setString(1, foodName);

            System.out.println("음식의 가격을 등록해 주십시오 : ");
            int menuPrice = scr.nextInt();
            pstmt.setInt(2, menuPrice);

            System.out.println("음식의 장르를 정해 주십시오. \n" +
                    "1: 한식\n" +
                    "2: 중식\n" +
                    "3: 일식\n" +
                    "4: 퓨전");
            int menuCategory = scr.nextInt();
            pstmt.setInt(3, menuCategory);
            scr.nextLine();

            System.out.println("음식 판매에 관한 활성화 여부를 알려 주십시오. \n" +
                    "Y: 주문 가능\n" +
                    "N: 주문 불가능");
            String menuStatus = scr.nextLine();
            pstmt.setString(4, menuStatus);

            result = pstmt.executeUpdate();
            if(result == 1) {System.out.println("등록되었습니다.");
            }else{
                System.out.println("등록에 실패하였습니다. 입력하신 내용을 다시 확인해서 입력해 주세요.");
            }


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

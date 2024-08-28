package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();
        Properties prop = new Properties();

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;


        ResultSet rset1 = null;
        ResultSet rset2 = null;
        int result3 = 0;
        List<Map<Integer, String>> categoryList2 = null;


        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query1 = prop.getProperty("selectLastMenuCode");
            String query2 = prop.getProperty("selectAllCategoryList");
            String query3 = prop.getProperty("insertMenu");

            pstmt1 = con.prepareStatement(query1);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);


            rset1 = pstmt1.executeQuery();
            if (rset1.next()) { // 0부터 센다. 현재 결과값이 하나 뿐인데, 그 다음이 존재 할 수 없는데 트루가 떴다. 1부터가 아니고 0부터 센다.
                result = rset1.getInt("MAX(MENU_CODE)");
            }
            System.out.println("가장 최근에 추가된 메뉴 코드 : " + result);

            System.out.println("--------------------------------------------------------------------------------------");

            rset2 = pstmt2.executeQuery();
            categoryList2 = new ArrayList<>();
            while (rset2.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                categoryList2.add(category);
            }
            System.out.println("categoryList = " + categoryList2);

            System.out.println("---------------------------------------------------------------------------------------");

            pstmt3.setString(1,"사람고기");
            pstmt3.setInt(2,4);
            pstmt3.setInt(3,5);
            pstmt3.setString(4,"N");
            result3 = pstmt3.executeUpdate();
            if (result3==1){
                System.out.println("메뉴가 추가되었소!");
            }else{
                System.out.println("메뉴 추가에 실패했소! 확해보시오~~!");
            }

            System.out.println("---------------------------------------------------------------------------------------");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt1);
            close(rset1);
            close(pstmt2);
            close(rset2);
            close(pstmt3);

        }

    }
}

package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section02.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {
    // 데이터 엑세스 오브젝트 - 데이터베이스와 상호작용을 할 클래스

    private Properties prop = new Properties();

    public MenuDAO(String url) { // 생성자를 만들 때 url을 받도록 강제.
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastMenuCode(Connection con) { // 클래스의 단일 책임 이론에 의해서 값만을 받아낸다.
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode");

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                maxCode = rset.getInt("MAX(MENU_CODE)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return maxCode; // 가장 최신 메뉴 코드
    }

    public List<Map<Integer, String>> selectAllCategory(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<Map<Integer, String>> allCategorylist = new ArrayList<>();

        String query = prop.getProperty("selectAllCategoryList");

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));
                allCategorylist.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return allCategorylist;
    }

    public int insertMenu(Connection con, MenuDTO menuDTO) { // DTO 정보의 강제화
        PreparedStatement pstmt = null; // ? 로 받을 거니까, 미완성 쿼리니까
        int result = 0; // 실행 성공 여부

        String query = prop.getProperty("insertMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, menuDTO.getName());
            pstmt.setInt(2, menuDTO.getPrice());
            pstmt.setInt(3,menuDTO.getCategoryCode());
            pstmt.setString(4, menuDTO.getStatus());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("잘 못된 값이 입력됨..");
        }finally {
            close(con);
            close(pstmt);
        }
        return result;
    }


}

package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Connection conn = getConnection();
        PreparedStatement ps = null;
        int result = 0;
        Properties props = new Properties();

        Scanner scr = new Scanner(System.in);

        System.out.println("삭제할 메뉴의 이름이 어찌 되오?");
        String a = scr.nextLine();

        try {
            props.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            ps = conn.prepareStatement(props.getProperty("deleteMenu"));

            ps.setString(1, a);

            result = ps.executeUpdate();

            if(result == 1) {
                System.out.println("좋소! 그 메뉴 나도 별로였소~!! 👌");
            }else {
                System.out.println("그 런 메 뉴 는 없 소 . . . 확인해 보쇼! 🤣;) ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(conn);
            close(ps);
        }

    }
}

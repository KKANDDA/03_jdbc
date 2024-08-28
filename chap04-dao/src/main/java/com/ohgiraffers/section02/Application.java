package com.ohgiraffers.section02;

import com.ohgiraffers.section02.controller.MenuController;

import java.util.Scanner;

import static java.awt.SystemColor.menu;

public class Application {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        MenuController menuController = new MenuController();

        while (true){
            System.out.println("사용할 기능을 선택해 주세요.");
            System.out.println("1. 가장 최신 메뉴 코드 조회");
            System.out.println("2. 모든 카테고리 목록 조회");
            System.out.println("3. 메뉴 등록");
            System.out.println("9. 프로그램 종료");
            int choice = scr.nextInt();

            switch (choice) {
                case 1: menuController.findMaxCode(); break;
                case 2: menuController.findAllCategoryList(); break;
                case 3: menuController.insertMenu(); break; // 메뉴 컨트롤러의 인설트메뉴 메소드 호출
                case 9: break;
                default:
                    System.out.println("잘 못된 입력입니다.");
                    break;
            }
        }
    }
}

package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;
import com.ohgiraffers.section02.dto.MenuDTO;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection2;

public class MenuController {

    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");


    public void findMaxCode(){
        int result = menuDAO.selectLastMenuCode(getConnection2());
        System.out.println("최신 메뉴 코드 : " + result);
    }


    public void findAllCategoryList(){
        List<Map<Integer, String>> allCategoryAllList;
        allCategoryAllList = menuDAO.selectAllCategory(getConnection2());

        System.out.println("카테고리 코드와 그 이름 : " + allCategoryAllList);
    }

    public void insertMenu(){ // 반환값 없음
        Scanner scr = new Scanner(System.in);
        MenuDTO menuDTO = new MenuDTO(); // DTO 클래스 사용 할 거야!!
        System.out.println("메뉴 이름을 입력해 주세요");
        menuDTO.menuName(scr.nextLine());
        System.out.println("메뉴 가격을 입력해 주세요");
        menuDTO.price(scr.nextInt());
        System.out.println("카테고리 번호를 입력해 주세요");
        menuDTO.categoryCode(scr.nextInt());
        System.out.println("판매 여부를 등록해 주세요");
        scr.nextLine();
        menuDTO.status(scr.nextLine());
        // DTO에 값을 넣었어!!

        int result = menuDAO.insertMenu(getConnection2(), menuDTO); // 상기 입력받은 DTO정보를 보냈다.
        if(result> 0){
            System.out.println("메뉴 등록 완료");
       }else{
            System.out.println("메뉴 등록 실패");
        }
    }


}

package com.ohgiraffers.section01;

import com.ohgiraffers.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.*;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application05 {
    public static void main(String[] args) {
        
        // 여러 DTO를 하나의 List 로 묶어서 처리
        
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        
        // 한 행의 정보를 담을 DTO
        EmployeeDTO row = null;

        // 여러 DTO를 묶을 List
        List<EmployeeDTO> empList = null;

        String query = "SELECT * FROM EMPLOYEE";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            empList = new ArrayList<>(); // try 밖에서 선언하고

            while (rset.next()) {

                row = new EmployeeDTO();
                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMail"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row); // 와일문 안에서 만들어지고 들어가고 만들어지고 들어가고.. 반복



            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt); // try 밖에서 선언했기 때문에 try 밖에선 null일 수 있다는 뜻
            close(rset);
        }

        for (EmployeeDTO emp : empList) {
            System.out.println(emp);
        }

    }
}

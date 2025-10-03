package org.example.employeejdbc;

import org.example.jdbc.MakeConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementApp {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = MakeConnection.getConnection();
        EmployeeDAO dao = new EmployeeDAO(conn);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter department name:");
        String dept = sc.nextLine();
        List<Employee> employees = dao.getEmployeesByDepartment(dept);
        for (Employee e : employees) {
            System.out.println(e.getEmpId() + " " + e.getName() + " " + e.getDesignation() + " " + e.getSalary());
        }

        System.out.println("Insert new employee: emp_id, name, designation, salary, dept_id, tax_id");
        int id = sc.nextInt();
        sc.nextLine();
        String name = sc.nextLine();
        String des = sc.nextLine();
        double salary = sc.nextDouble();
        int deptId = sc.nextInt();
        sc.nextLine();
        String taxId = sc.nextLine();
        Employee newEmp = new Employee(id, name, des, salary, deptId, new Date(), taxId, true);
        dao.insertEmployee(newEmp);

        System.out.println("Update salary of employee by emp_id:");
        int empIdToUpdate = sc.nextInt();
        double newSalary = sc.nextDouble();
        dao.updateSalary(empIdToUpdate, newSalary);

        System.out.println("Delete employee by emp_id:");
        int empIdToDelete = sc.nextInt();
        dao.deleteEmployee(empIdToDelete);

        sc.close();
        conn.close();
    }

}

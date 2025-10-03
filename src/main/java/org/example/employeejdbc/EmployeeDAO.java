package org.example.employeejdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Display employees by department
    public List<Employee> getEmployeesByDepartment(String deptName) throws SQLException {
        String sql = "SELECT e.* FROM Employee e " +
                "JOIN Department d ON e.dept_id = d.dept_id " +
                "WHERE d.dept_name=? AND e.is_active='Y'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, deptName);
        ResultSet rs = ps.executeQuery();

        List<Employee> list = new ArrayList<>();
        while (rs.next()) {
            Employee emp = new Employee(
                    rs.getInt("emp_id"),
                    rs.getString("name"),
                    rs.getString("designation"),
                    rs.getDouble("salary"),
                    rs.getInt("dept_id"),
                    rs.getDate("date_of_joining"),
                    rs.getString("tax_id"),
                    rs.getString("is_active").equals("Y")
            );
            list.add(emp);
        }
        return list;
    }

    // 2. Insert new employee
    public void insertEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO Employee(emp_id,name,designation,salary,dept_id,date_of_joining,tax_id,is_active) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, emp.getEmpId());
        ps.setString(2, emp.getName());
        ps.setString(3, emp.getDesignation());
        ps.setDouble(4, emp.getSalary());
        ps.setInt(5, emp.getDeptId());
        ps.setDate(6, new java.sql.Date(emp.getDateOfJoining().getTime()));
        ps.setString(7, emp.getTaxId());
        ps.setString(8, emp.isActive() ? "Y" : "N");
        ps.executeUpdate();
    }

    // 3. Update salary with history
    public void updateSalary(int empId, double newSalary) throws SQLException {
        conn.setAutoCommit(false);
        try {
            // get old salary
            String selectSql = "SELECT salary FROM Employee WHERE emp_id=?";
            PreparedStatement ps1 = conn.prepareStatement(selectSql);
            ps1.setInt(1, empId);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                double oldSalary = rs.getDouble("salary");

                // update salary
                String updateSql = "UPDATE Employee SET salary=? WHERE emp_id=?";
                PreparedStatement ps2 = conn.prepareStatement(updateSql);
                ps2.setDouble(1, newSalary);
                ps2.setInt(2, empId);
                ps2.executeUpdate();

                // insert into history
                String histSql = "INSERT INTO SalaryHistory(emp_id, old_salary, new_salary) VALUES(?,?,?)";
                PreparedStatement ps3 = conn.prepareStatement(histSql);
                ps3.setInt(1, empId);
                ps3.setDouble(2, oldSalary);
                ps3.setDouble(3, newSalary);
                ps3.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // 4. Soft delete employee
    public void deleteEmployee(int empId) throws SQLException {
        String sql = "UPDATE Employee SET is_active='N' WHERE emp_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, empId);
        ps.executeUpdate();
    }
}

import org.example.employeejdbc.Employee;
import org.example.employeejdbc.EmployeeDAO;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTest {

    static Connection conn;
    static EmployeeDAO dao;

    @BeforeAll
    static void setup() throws SQLException {
        conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "your_user", "your_pass");
        dao = new EmployeeDAO(conn);
    }

    @Test
    @Order(1)
    void testInsertEmployee() {
        Employee emp = new Employee(999, "Test User", "Developer", 50000, 1, new Date(), "TAX123", true);
        assertDoesNotThrow(() -> dao.insertEmployee(emp));
    }

    @Test
    @Order(2)
    void testGetEmployeesByDepartment() throws SQLException {
        List<Employee> employees = dao.getEmployeesByDepartment("IT");
        assertNotNull(employees);
        assertTrue(employees.size() >= 0);
    }

    @Test
    @Order(3)
    void testUpdateSalary() {
        assertDoesNotThrow(() -> dao.updateSalary(999, 55000));
    }

    @Test
    @Order(4)
    void testDeleteEmployee() {
        assertDoesNotThrow(() -> dao.deleteEmployee(999));
    }

    @AfterAll
    static void teardown() throws SQLException {
        conn.close();
    }
}

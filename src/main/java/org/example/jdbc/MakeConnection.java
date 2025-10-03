package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver"); // Load Oracle JDBC Driver

        // Proper JDBC URL format for SID-based connection
        return DriverManager.getConnection(
                "jdbc:oracle:thin:1521:orcl", "scott", "tiger"
        );
    }
}

package org.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManagement {
    public static void bigQuery() {
        String sql =
                "SELECT * " +
                        "FROM ( " +
                        "    SELECT c.CustomerID, c.FullName, " +
                        "           SUM(od.Quantity * od.Price) AS TotalSpent, " +
                        "           COUNT(DISTINCT p.CategoryID) AS CategoryCount " +
                        "    FROM Customers c " +
                        "    JOIN Orders o ON c.CustomerID = o.CustomerID " +
                        "    JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                        "    JOIN Products p ON od.ProductID = p.ProductID " +
                        "    WHERE o.OrderDate >= ADD_MONTHS(SYSDATE, -6) " +
                        "    GROUP BY c.CustomerID, c.FullName " +
                        "    HAVING COUNT(DISTINCT p.CategoryID) >= 3 " +
                        "    ORDER BY TotalSpent DESC " +
                        ") " +
                        "WHERE ROWNUM <= 3";

        try (Connection conn = MakeConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Top 3 Customers (by spending, >= 3 categories, last 6 months):");
            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String fullName = rs.getString("FullName");
                double totalSpent = rs.getDouble("TotalSpent");
                int categoryCount = rs.getInt("CategoryCount");

                System.out.printf("ID: %d | Name: %s | Spent: %.2f | Categories: %d%n",
                        customerId, fullName, totalSpent, categoryCount);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

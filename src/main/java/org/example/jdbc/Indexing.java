package org.example.jdbc;

import java.sql.*;

public class Indexing {
    private static Connection connection;

    static {
        try {
            connection = MakeConnection.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void last30Order() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM Orders WHERE OrderDate >= SYSDATE - 30";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int orderId = rs.getInt("OrderID");
            Date orderDate = rs.getDate("OrderDate");
            String customer = rs.getString("CustomerName"); // adjust column names as needed

            System.out.println("Order ID: " + orderId + ", Date: " + orderDate + ", Customer: " + customer);
        }
    }

    static void getProductByCategory() throws SQLException {
        String category = "Electronics";
        String query = "SELECT p.ProductID, p.ProductName, p.Price, c.CategoryName " +
                "FROM Products p " +
                "JOIN Categories c ON p.CategoryID = c.CategoryID " +
                "WHERE c.CategoryName = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                String catName = rs.getString("CategoryName");

                System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price + ", Category: " + catName);
            }
        }

    }

    static void orderAndCategory() {
        String categoryName = "Electronics"; // Example category
        String query = "SELECT p.ProductID, p.ProductName, p.Price, c.CategoryName " +
                "FROM Products p " +
                "JOIN Categories c ON p.CategoryID = c.CategoryID " +
                "WHERE c.CategoryName = ?";

        try
                (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                String category = rs.getString("CategoryName");

                System.out.println("ID: " + productId + ", Name: " + productName +
                        ", Price: ₹" + price + ", Category: " + category);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}

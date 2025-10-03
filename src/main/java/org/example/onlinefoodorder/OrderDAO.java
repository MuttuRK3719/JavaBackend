package org.example.onlinefoodorder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {


    private Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Insert new order + update last_order_date atomically
    public void insertOrder(Order order) throws SQLException {
        conn.setAutoCommit(false);
        try {
            // Insert order
            String orderSql = "INSERT INTO Orders(order_id, cust_id, order_date, amount) VALUES(?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(orderSql);
            ps1.setInt(1, order.getOrderId());
            ps1.setInt(2, order.getCustId());
            ps1.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            ps1.setDouble(4, order.getAmount());
            ps1.executeUpdate();

            // Update last_order_date in Customer
            String updateSql = "UPDATE Customer SET last_order_date=? WHERE cust_id=?";
            PreparedStatement ps2 = conn.prepareStatement(updateSql);
            ps2.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
            ps2.setInt(2, order.getCustId());
            ps2.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // 2. Fetch order history of a customer
    public List<Order> getOrdersByCustomer(int custId) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE cust_id=? ORDER BY order_date";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, custId);
        ResultSet rs = ps.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            orders.add(new Order(
                    rs.getInt("order_id"),
                    rs.getInt("cust_id"),
                    rs.getDate("order_date"),
                    rs.getDouble("amount")
            ));
        }
        return orders;
    }

    // 3. Get top 3 customers by total purchase
    public List<Customer> getTopCustomers() throws SQLException {
        String sql = "SELECT c.cust_id, c.cust_name, c.email, c.last_order_date, SUM(o.amount) as total " +
                "FROM Customer c LEFT JOIN Orders o ON c.cust_id=o.cust_id " +
                "GROUP BY c.cust_id, c.cust_name, c.email, c.last_order_date " +
                "ORDER BY total DESC NULLS LAST FETCH FIRST 3 ROWS ONLY";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Customer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Customer(
                    rs.getInt("cust_id"),
                    rs.getString("cust_name"),
                    rs.getString("email"),
                    rs.getDate("last_order_date")
            ));
        }
        return list;
    }

    // 4. Update order amount with history
    public void updateOrderAmount(int orderId, double newAmount) throws SQLException {
        conn.setAutoCommit(false);
        try {
            // get old amount
            String selectSql = "SELECT amount FROM Orders WHERE order_id=?";
            PreparedStatement ps1 = conn.prepareStatement(selectSql);
            ps1.setInt(1, orderId);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                double oldAmount = rs.getDouble("amount");

                // update amount
                String updateSql = "UPDATE Orders SET amount=? WHERE order_id=?";
                PreparedStatement ps2 = conn.prepareStatement(updateSql);
                ps2.setDouble(1, newAmount);
                ps2.setInt(2, orderId);
                ps2.executeUpdate();

                // insert into history
                String histSql = "INSERT INTO OrderHistory(order_id, old_amount, new_amount) VALUES(?,?,?)";
                PreparedStatement ps3 = conn.prepareStatement(histSql);
                ps3.setInt(1, orderId);
                ps3.setDouble(2, oldAmount);
                ps3.setDouble(3, newAmount);
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
}

package org.example.onlinefoodorder;

import org.example.jdbc.MakeConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OnlineOrderApp {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = MakeConnection.getConnection();
        OrderDAO dao = new OrderDAO(conn);
        Scanner sc = new Scanner(System.in);

        // Insert new order
        System.out.println("Insert order: order_id, cust_id, amount");
        int orderId = sc.nextInt();
        int custId = sc.nextInt();
        double amount = sc.nextDouble();
        dao.insertOrder(new Order(orderId, custId, new Date(), amount));
        System.out.println("Order inserted and last_order_date updated.");

        // Show order history
        System.out.println("Enter customer id to fetch orders:");
        int cid = sc.nextInt();
        List<Order> orders = dao.getOrdersByCustomer(cid);
        for (Order o : orders) {
            System.out.println(o.getOrderId() + " " + o.getOrderDate() + " " + o.getAmount());
        }

        // Show top 3 customers
        System.out.println("Top 3 customers by purchase:");
        List<Customer> topCusts = dao.getTopCustomers();
        for (Customer c : topCusts) {
            System.out.println(c.getCustId() + " " + c.getName() + " " + c.getLastOrderDate());
        }

        sc.close();
        conn.close();
    }
}


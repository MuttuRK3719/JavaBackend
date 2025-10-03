package org.example.onlinefoodorder;

import java.util.Date;

public class Order {
    private int orderId;
    private int custId;
    private Date orderDate;
    private double amount;

    public Order(int orderId, int custId, Date orderDate, double amount) {
        this.orderId = orderId;
        this.custId = custId;
        this.orderDate = orderDate;
        this.amount = amount;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public int getCustId() {
        return custId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getAmount() {
        return amount;
    }
}


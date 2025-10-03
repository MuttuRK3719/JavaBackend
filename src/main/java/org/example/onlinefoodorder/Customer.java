package org.example.onlinefoodorder;

import java.util.Date;

public class Customer {
    private int custId;
    private String name;
    private String email;
    private Date lastOrderDate;

    public Customer(int custId, String name, String email, Date lastOrderDate) {
        this.custId = custId;
        this.name = name;
        this.email = email;
        this.lastOrderDate = lastOrderDate;
    }

    // Getters and setters
    public int getCustId() {
        return custId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }
}


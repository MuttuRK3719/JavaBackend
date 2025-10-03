package org.example.employeejdbc;

import java.util.Date;

public class Employee {
    private int empId;
    private String name;
    private String designation;
    private double salary;
    private int deptId;
    private Date dateOfJoining;
    private String taxId;
    private boolean isActive;

    // Constructor
    public Employee(int empId, String name, String designation, double salary,
                    int deptId, Date dateOfJoining, String taxId, boolean isActive) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.deptId = deptId;
        this.dateOfJoining = dateOfJoining;
        this.taxId = taxId;
        this.isActive = isActive;
    }

    // Getters and setters
    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public int getDeptId() {
        return deptId;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public String getTaxId() {
        return taxId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
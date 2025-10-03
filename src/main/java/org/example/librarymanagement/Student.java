package org.example.librarymanagement;

public class Student {
    private int studentId;
    private String name;
    private String branch;

    public Student(int studentId, String name, String branch) {
        this.studentId = studentId;
        this.name = name;
        this.branch = branch;
    }

    // Getters
    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
}

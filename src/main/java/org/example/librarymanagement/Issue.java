package org.example.librarymanagement;

import java.util.Date;

public class Issue {
    private int issueId;
    private int studentId;
    private int bookId;
    private Date issueDate;
    private Date returnDate;

    public Issue(int issueId, int studentId, int bookId, Date issueDate, Date returnDate) {
        this.issueId = issueId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    // Getters and setters
    public int getIssueId() { return issueId; }
    public int getStudentId() { return studentId; }
    public int getBookId() { return bookId; }
    public Date getIssueDate() { return issueDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
}

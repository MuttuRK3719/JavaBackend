package org.example.librarymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "your_user";
    static final String PASS = "your_pass";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        LibraryDAO dao = new LibraryDAO(conn);
        Scanner sc = new Scanner(System.in);

        // Issue book
        System.out.println("Enter issue_id, student_id, book_id:");
        int issueId = sc.nextInt();
        int studentId = sc.nextInt();
        int bookId = sc.nextInt();
        dao.issueBook(new Issue(issueId, studentId, bookId, new Date(), null));
        System.out.println("Book issued successfully.");

        // Return book
        System.out.println("Enter issue_id to return:");
        int returnIssueId = sc.nextInt();
        dao.returnBook(returnIssueId, (java.sql.Date) new Date());
        System.out.println("Book returned successfully.");

        // Search book
        sc.nextLine(); // consume newline
        System.out.println("Enter keyword to search book:");
        String keyword = sc.nextLine();
        List<Book> books = dao.searchBook(keyword);
        for (Book b : books) {
            System.out.println(b.getBookId() + " " + b.getTitle() + " " + b.getAuthor() + " Available: " + b.getAvailableCopies());
        }

        // Student-wise issue history
        System.out.println("Enter student_id to get issue history:");
        int sid = sc.nextInt();
        List<Issue> history = dao.getStudentIssueHistory(sid);
        for (Issue i : history) {
            System.out.println(i.getIssueId() + " Book: " + i.getBookId() + " Issue Date: " + i.getIssueDate() + " Return Date: " + i.getReturnDate());
        }

        sc.close();
        conn.close();
    }
}

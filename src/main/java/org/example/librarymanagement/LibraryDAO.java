package org.example.librarymanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {
    private Connection conn;

    public LibraryDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Issue a book (reduce available copies)
    public void issueBook(Issue issue) throws SQLException {
        conn.setAutoCommit(false);
        try {
            // Check availability
            String checkSql = "SELECT available_copies FROM Book WHERE book_id=?";
            PreparedStatement ps1 = conn.prepareStatement(checkSql);
            ps1.setInt(1, issue.getBookId());
            ResultSet rs = ps1.executeQuery();
            if(rs.next() && rs.getInt("available_copies") > 0) {

                // Insert into Issue table
                String insertSql = "INSERT INTO Issue(issue_id, student_id, book_id, issue_date, return_date) VALUES(?,?,?,?,?)";
                PreparedStatement ps2 = conn.prepareStatement(insertSql);
                ps2.setInt(1, issue.getIssueId());
                ps2.setInt(2, issue.getStudentId());
                ps2.setInt(3, issue.getBookId());
                ps2.setDate(4, new java.sql.Date(issue.getIssueDate().getTime()));
                ps2.setDate(5, null);
                ps2.executeUpdate();

                // Reduce available copies
                String updateSql = "UPDATE Book SET available_copies = available_copies - 1 WHERE book_id=?";
                PreparedStatement ps3 = conn.prepareStatement(updateSql);
                ps3.setInt(1, issue.getBookId());
                ps3.executeUpdate();

                conn.commit();
            } else {
                System.out.println("Book not available.");
                conn.rollback();
            }
        } catch(SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // 2. Return a book (update return_date + increase available copies)
    public void returnBook(int issueId, Date returnDate) throws SQLException {
        conn.setAutoCommit(false);
        try {
            // Get issue details
            String selectSql = "SELECT book_id, return_date FROM Issue WHERE issue_id=?";
            PreparedStatement ps1 = conn.prepareStatement(selectSql);
            ps1.setInt(1, issueId);
            ResultSet rs = ps1.executeQuery();
            if(rs.next() && rs.getDate("return_date") == null) {
                int bookId = rs.getInt("book_id");

                // Update Issue table
                String updateIssue = "UPDATE Issue SET return_date=? WHERE issue_id=?";
                PreparedStatement ps2 = conn.prepareStatement(updateIssue);
                ps2.setDate(1, new java.sql.Date(returnDate.getTime()));
                ps2.setInt(2, issueId);
                ps2.executeUpdate();

                // Insert into IssueHistory
                String histSql = "INSERT INTO IssueHistory(issue_id, old_return_date, new_return_date) VALUES(?,?,?)";
                PreparedStatement ps3 = conn.prepareStatement(histSql);
                ps3.setInt(1, issueId);
                ps3.setDate(2, null);
                ps3.setDate(3, new java.sql.Date(returnDate.getTime()));
                ps3.executeUpdate();

                // Increase available copies
                String updateBook = "UPDATE Book SET available_copies = available_copies + 1 WHERE book_id=?";
                PreparedStatement ps4 = conn.prepareStatement(updateBook);
                ps4.setInt(1, bookId);
                ps4.executeUpdate();

                conn.commit();
            } else {
                System.out.println("Invalid issue ID or book already returned.");
                conn.rollback();
            }
        } catch(SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // 3. Search book by title or author
    public List<Book> searchBook(String keyword) throws SQLException {
        String sql = "SELECT * FROM Book WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword.toLowerCase() + "%");
        ps.setString(2, "%" + keyword.toLowerCase() + "%");
        ResultSet rs = ps.executeQuery();

        List<Book> list = new ArrayList<>();
        while(rs.next()) {
            list.add(new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("total_copies"),
                    rs.getInt("available_copies")
            ));
        }
        return list;
    }

    // 4. Student-wise issue history
    public List<Issue> getStudentIssueHistory(int studentId) throws SQLException {
        String sql = "SELECT * FROM Issue WHERE student_id=? ORDER BY issue_date";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, studentId);
        ResultSet rs = ps.executeQuery();

        List<Issue> list = new ArrayList<>();
        while(rs.next()) {
            list.add(new Issue(
                    rs.getInt("issue_id"),
                    rs.getInt("student_id"),
                    rs.getInt("book_id"),
                    rs.getDate("issue_date"),
                    rs.getDate("return_date")
            ));
        }
        return list;
    }
}

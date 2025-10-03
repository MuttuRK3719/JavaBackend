package org.example.librarymanagement;

public class Book {
        private int bookId;
        private String title;
        private String author;
        private int totalCopies;
        private int availableCopies;

        public Book(int bookId, String title, String author, int totalCopies, int availableCopies) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.totalCopies = totalCopies;
            this.availableCopies = availableCopies;
        }

        // Getters and setters
        public int getBookId() { return bookId; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public int getTotalCopies() { return totalCopies; }
        public int getAvailableCopies() { return availableCopies; }
        public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
    }


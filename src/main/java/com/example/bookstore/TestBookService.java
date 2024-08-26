package com.example.bookstore;

public class TestBookService {
    public static void main(String[] args) {
        try {
            // Initialize Firebase
            FirebaseInitializer.initialize();
            
            // Create BookService instance
            BookService bookService = new BookService();
            
            // Create a new book
            Book newBook = new Book("1", "The Great Gatsby", "F. Scott Fitzgerald", 1925);
            bookService.createBook(newBook);
            
            // Retrieve the book
            Book retrievedBook = bookService.getBookById("1");
            System.out.println("Retrieved Book: " + retrievedBook);
            
            // Update the book
            Book updatedBook = new Book("1", "The Great Gatsby", "F. Scott Fitzgerald", 1926);
            bookService.updateBook(updatedBook);
            
            // Delete the book
            bookService.deleteBook("1");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

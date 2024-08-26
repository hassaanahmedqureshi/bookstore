package com.example.bookstore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

public class BookService {
    private final Firestore db;

    public BookService() {
        this.db = FirestoreOptions.getDefaultInstance().getService();  // Use the initialized Firestore instance
    }

    // Create a book
    public void createBook(Book book) throws InterruptedException, ExecutionException {
        CollectionReference books = db.collection("books");
        DocumentReference docRef = books.document(book.getId());
        ApiFuture<WriteResult> future = docRef.set(toMap(book));
        future.get(); // Wait for the write to complete
        System.out.println("Book created with ID: " + book.getId());
    }

    // Read a book by ID
    public Book getBookById(String id) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("books").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return fromMap(document.getData());
        } else {
            throw new RuntimeException("No such document!");
        }
    }

    // Update a book
    public void updateBook(Book book) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("books").document(book.getId());
        ApiFuture<WriteResult> future = docRef.update(toMap(book));
        future.get(); // Wait for the write to complete
        System.out.println("Book updated with ID: " + book.getId());
    }

    // Delete a book
    public void deleteBook(String id) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("books").document(id);
        ApiFuture<WriteResult> future = docRef.delete();
        future.get(); // Wait for the delete to complete
        System.out.println("Book deleted with ID: " + id);
    }

    // Helper method to convert Book to Map
    private Map<String, Object> toMap(Book book) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", book.getTitle());
        map.put("author", book.getAuthor());
        map.put("year", book.getYear());
        return map;
    }

    // Helper method to convert Map to Book
    private Book fromMap(Map<String, Object> map) {
        String id = (String) map.get("id");
        String title = (String) map.get("title");
        String author = (String) map.get("author");
        int year = ((Long) map.get("year")).intValue();
        return new Book(id, title, author, year);
    }
}

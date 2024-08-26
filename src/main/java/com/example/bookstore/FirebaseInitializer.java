package com.example.bookstore;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInitializer {
    public static void main(String[] args) {
        try {
            initialize();
            System.out.println("Firebase initialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase.");
        }
    }

    public static void initialize() throws IOException {
        // Ensure the path to the service account key is correct
        String path = null;
        try {
            path = Paths.get(FirebaseInitializer.class.getClassLoader().getResource("serviceAccountKey.json").toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.err.println("Failed to get resource path.");
            throw new IOException("Failed to get resource path.", e);
        }

        FileInputStream serviceAccount = new FileInputStream(path);

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://book-store-3ae24-default-rtdb.europe-west1.firebasedatabase.app/") // Replace with your actual database URL
            .build();

        FirebaseApp.initializeApp(options);
    }
}

package com.example.bookstore;

import java.io.FileInputStream;
import java.io.IOException;

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
        String path = FirebaseInitializer.class.getClassLoader().getResource("serviceAccountKey.json").getFile();
        FileInputStream serviceAccount = new FileInputStream(path);

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://your-database-name.firebaseio.com")
            .build();

        FirebaseApp.initializeApp(options);
    }
}
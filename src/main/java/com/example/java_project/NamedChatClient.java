package com.example.java_project;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NamedChatClient   {

    private PrintWriter output;

    public void chat(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Named Chat Client");

        // Scene for entering the name
        VBox nameLayout = new VBox(10);
        TextField nameField = new TextField();
        Button connectButton = new Button("Connect to Chat");
        Label nameLabel = new Label("Enter your name:");

        connectButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                connectToServer(primaryStage, name);
            }
        });

        nameLayout.getChildren().addAll(nameLabel, nameField, connectButton);
        Scene nameScene = new Scene(nameLayout, 300, 150);

        primaryStage.setScene(nameScene);
        primaryStage.showAndWait();

    }

    private void connectToServer(Stage primaryStage, String name) {
        try {
            Socket socket = new Socket("localhost", 5555);

            // Display the server's welcome message
            Scanner nameScanner = new Scanner(socket.getInputStream());
            String serverMessage = nameScanner.nextLine();


            // Switch to the chat scene
            Platform.runLater(() -> {
                try {
                    switchToChatScene(primaryStage, socket, name);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToChatScene(Stage primaryStage, Socket socket, String name) throws IOException {
        // UI Components for the chat scene
        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);

        TextField messageField = new TextField();
        messageField.setOnAction(e -> {
            sendMessage(messageField.getText());
            messageField.clear();
        });

        // Layout
        VBox layout = new VBox(10);
        Button toMain = new Button("Chat");
        toMain.setMinWidth(100);
        layout.getChildren().addAll(chatArea, messageField);

        // Scene for the chat
        Scene chatScene = new Scene(layout, 300, 210);
        primaryStage.setScene(chatScene);

        // Show the stage
        primaryStage.show();

        // Get the client's name
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println(name);

        // Start a new thread to listen for incoming messages
        Thread thread = new Thread(() -> {
            try {
                Scanner input = new Scanner(socket.getInputStream());
                while (input.hasNextLine()) {
                    String message = input.nextLine();
                    Platform.runLater(() -> chatArea.appendText(message + "\n"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void sendMessage(String message) {
        if (!message.trim().isEmpty()) {
            output.println(message);
        }
        // mysql inserting data
    }
    private void getAllMessageFromDatabase(){

    }

}

package com.example.java_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class server {

    private static Set<PrintWriter> clients = new HashSet<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // Ask for the client's name
                PrintWriter nameWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                nameWriter.println("Enter your name:");
                Scanner nameScanner = new Scanner(clientSocket.getInputStream());
                String name = nameScanner.nextLine().trim();
                nameWriter.println("Hello, " + name + "! You are now connected.");

                // Create a writer for the client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(writer);

                // Broadcast the new user joining
                broadcastMessage(name + " has joined the chat.");

                // Start a new thread to handle the client's messages
                Thread clientThread = new Thread(new ClientHandler(clientSocket, writer, name));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {

        private Socket clientSocket;
        private PrintWriter clientWriter;
        private String clientName;
        private Scanner input;

        public ClientHandler(Socket socket, PrintWriter writer, String name) {
            this.clientSocket = socket;
            this.clientWriter = writer;
            this.clientName = name;
            try {
                this.input = new Scanner(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (input.hasNextLine()) {
                        String message = input.nextLine();
                        System.out.println("Received from " + clientName + ": " + message);
                        broadcastMessage(clientName + ": " + message);
                    }
                }
            } finally {
                input.close();
            }
        }
    }

    private static void broadcastMessage(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
        }
    }
}

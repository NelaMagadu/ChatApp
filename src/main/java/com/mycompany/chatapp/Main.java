/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        ArrayList<Message> messages = new ArrayList<>();
        int messageCounter = 0;

        // Part 1: Registration and Login
        System.out.println("=== USER REGISTRATION ===");
        System.out.print("Enter a username: ");
        String username = input.nextLine();
        System.out.print("Enter a password: ");
        String password = input.nextLine();
        System.out.print("Enter your SA phone number (+27...): ");
        String phone = input.nextLine();
        System.out.println(login.registerUser(username, password, phone));

        System.out.println("\n=== USER LOGIN ===");
        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();
        System.out.print("Enter your password: ");
        String loginPassword = input.nextLine();
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loggedIn));

        // Part 2: Messaging System
        if (loggedIn) {
            System.out.println("Welcome to ChatApp."); // Exact for tests

            boolean running = true;
            while (running) { // Step 2.2: While loop
                System.out.println("\n--- MENU ---");
                System.out.println("1. Send Message");
                System.out.println("2. Show Recently Sent Messages");
                System.out.println("3. Quit");
                System.out.print("Choose an option: ");

                String choice = input.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("How many messages would you like to send? ");
                        int numMessages = Integer.parseInt(input.nextLine());

                        for (int i = 0; i < numMessages; i++) { // Step 3.2: For loop
                            messageCounter++;
                            System.out.print("Enter recipient number (+27...): ");
                            String recipient = input.nextLine();
                            System.out.print("Enter message: ");
                            String text = input.nextLine();

                            Message msg = new Message(messageCounter, recipient, text);

                            // Check length and recipient
                            String lengthCheck = msg.checkMessageLength();
                            System.out.println(lengthCheck);

                            if (lengthCheck.equals("Message ready to send.")) {
                                System.out.println(msg.checkRecipientCell());

                                // Sub-menu
                                System.out.println("\nWhat would you like to do with this message?");
                                System.out.println("1. Send Message");
                                System.out.println("2. Disregard Message");
                                System.out.println("3. Store Message to send later");
                                System.out.print("Enter choice: ");
                                int option = Integer.parseInt(input.nextLine());

                                String result = msg.sentMessage(option);
                                System.out.println(result);

                                if (option == 1 || option == 3) {
                                    messages.add(msg);
                                    System.out.println(msg.printMessages());
                                }
                            }
                        }
                        System.out.println("Total messages sent: " + messages.size());
                        break;

                    case "2":
                        if (messages.isEmpty()) {
                            System.out.println("No messages sent yet.");
                        } else {
                            for (Message m : messages) {
                                System.out.println(m.printMessages());
                            }
                        }
                        break;

                    case "3":
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Login failed. Exiting program.");
        }
        input.close();
    }
}
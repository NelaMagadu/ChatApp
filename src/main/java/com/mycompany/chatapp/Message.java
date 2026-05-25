/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
  
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Step 5.1: Generate 10-digit random ID
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    public boolean checkMessageID() {
        return messageID.length() == 10 && messageID.matches("\\d{10}");
    }

    // Step 6.2: Check recipient format
    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Step 7.1: Check message length and return correct string
    public String checkMessageLength() {
        if (messageText.length() > 250) {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + ", please reduce the size.";
        } else {
            return "Message ready to send.";
        }
    }

    // Step 8.2: Create hash - first 2 digits of ID : message number : first word : last word
    public String createMessageHash() {
        String firstTwoDigits = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Step 9.1: Sub-menu for Send, Disregard, Store
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                storeMessage(); // call JSON method
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    // Step 10.2: Store message as JSON
    // Attribution: org.json library - https://mvnrepository.com/artifact/org.json/json
    public void storeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("messageID", this.messageID);
        obj.put("messageNumber", this.messageNumber);
        obj.put("messageHash", this.messageHash);
        obj.put("recipient", this.recipient);
        obj.put("messageText", this.messageText);

        try (FileWriter fw = new FileWriter("messages.json", true)) {
            fw.write(obj.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Step 9.2: Display message details in exact order
    public String printMessages() {
        return "\nMessage ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText;
    }

    // Getters for testing
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public int getMessageNumber() { return messageNumber; }
    public String getMessageText() { return messageText; }
}
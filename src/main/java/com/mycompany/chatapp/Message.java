/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
  
 import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    // Part 3 arrays
    private static List<String> sentMessages = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
        
        // Generate 10-digit random ID
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

    //Check recipient format
    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    //Check message length and return correct string
    public String checkMessageLength() {
        if (messageText.length() > 250) {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + ", please reduce the size.";
        } else {
            return "Message ready to send.";
        }
    }

    // Create hash - first 2 digits of ID : message number : first word : last word
    public String createMessageHash() {
        String firstTwoDigits = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Part 3: Updated - populate Lists based on choice
    public String sentMessage(int choice) {
        switch (choice) {
            case 1: // Send
                storeMessage();
                sentMessages.add(this.messageText);
                messageIDs.add(this.messageID);
                messageHashes.add(this.recipient);
                return "Message successfully sent.";
            case 2: // Disregard
                disregardedMessages.add(this.messageText);
                return "Press 0 to delete the message.";
            case 3: // Store
                storeMessage();
                storedMessages.add(this.messageText);
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

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

    // Display message details in exact order
    public String printMessages() {
        return "\nMessage ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText;
    }

    // Part 3: b. Display longest message
    public static String displayLongestMessage() {
        if (sentMessages.isEmpty()) return "No messages sent yet";
        String longest = sentMessages.get(0);
        for (int i = 1; i < sentMessages.size(); i++) {
            if (sentMessages.get(i)!= null && sentMessages.get(i).length() > longest.length()) {
                longest = sentMessages.get(i);
            }
        }
        return "Longest Message: " + longest + "\nLength: " + longest.length();
    }

    // Part 3: c. Search by messageID
    public static String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i)!= null && messageIDs.get(i).equals(id)) {
                return "Recipient: " + messageHashes.get(i) + "\nMessage: " + sentMessages.get(i);
            }
        }
        return "Message ID not found";
    }
    
// Part 3: d. Search by recipient
    public static String searchByRecipient(String recipient) {
        String result = "";
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i)!= null && messageHashes.get(i).equals(recipient)) {
                result += sentMessages.get(i) + "\n";
            }
        }
        return result.isEmpty()? "No messages for this recipient" : result;
    }

    // Part 3: e. Delete by hash/recipient
    public static String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i)!= null && messageHashes.get(i).equals(hash)) {
                String msg = sentMessages.get(i);
                sentMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        return "Message hash not found";
    }
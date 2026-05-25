/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.chatapp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    @Test
    public void testMessageLengthValid() {
        Message msg = new Message(1, "+27123456789", "Hello");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthInvalid() {
        String longText = "a".repeat(260);
        Message msg = new Message(1, "+27123456789", longText);
        assertEquals("Message exceeds 250 characters by 10, please reduce the size.",
                     msg.checkMessageLength());
    }

    @Test
    public void testRecipientValid() {
        Message msg = new Message(1, "+27123456789", "Hello");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientInvalid() {
        Message msg = new Message(1, "08575975889", "Hello");
        assertEquals("Cell phone number incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                     msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        // Using POE test data 1: +27718693002, "Hi Mike, can you join us for dinner tonight?"
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        // Note: ID is random, so we test format. For 100% match, you'd mock ID to "00..."
        assertTrue(msg.getMessageHash().contains(":1:HI"));
        assertTrue(msg.getMessageHash().endsWith("TONIGHT"));
    }

    @Test
    public void testSentMessageSend() {
        Message msg = new Message(1, "+27123456789", "Hello");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message(1, "+27123456789", "Hello");
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message(1, "+27123456789", "Hello");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}

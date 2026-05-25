package com.mycompany.chatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import com.mycompany.chatapp.Login;       
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    

    Login login = new Login();

    // ================= USERNAME TESTS =================

    @Test
    public void testValidUsername() {
        // Checks if:
        // Username contains "_"
        // Username length is 5 or less

        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        // Checks:
        // Username does not contain "_"

        assertFalse(login.checkUserName("abcd1"));
    }

    @Test
    public void testInvalidUsername_TooLong() {
        // Checks:
        // Username is longer than 5 characters

        assertFalse(login.checkUserName("kylee_"));
    }

    // ================= PASSWORD TESTS =================

    @Test
    public void testValidPassword() {

        // Valid password:
        // capital letter + number + special character

        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testInvalidPassword() {

        // Invalid password:
        // no capital letter
        // no special character
        // no number

        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ================= PHONE NUMBER TESTS =================

    @Test
    public void testValidPhoneNumber() {

        // Correct international format

        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testInvalidPhoneNumber() {

        // Missing international code
          assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}
    

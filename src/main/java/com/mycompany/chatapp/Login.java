/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
  

public class Login {
    private String username;
    private String password;
    private String phoneNumber;

     //Checks if username is valid: contains underscore and <= 5 characters.
     //return true if valid
     
    public boolean checkUserName(String name) {
        return name.contains("_") && name.length() <= 5; //  
    }

    
     // Checks password complexity: >= 8 chars, 1 capital, 1 number, 1 special char.
     //return true if valid
    public boolean checkPasswordComplexity(String password) {
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+].*");
        return password.length() >= 8 && hasUpper && hasDigit && hasSpecial; // Fixed: >= not >-
    }

    // Checks if cell number starts with +27 and is <= 12 characters.
     //return true if valid
    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() <= 12;
    }

    //Registers a user if all fields are valid.
     //username
     //password
     //phone number
     //return success or error messages
     
    public String registerUser(String name, String pass, String phone) {
        String userCheck = checkUserName(name) 
            ? "Username successfully captured." 
            : "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        
        String passCheck = checkPasswordComplexity(pass) 
            ? "Password successfully captured." 
            : "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        
        String phoneCheck = checkCellPhoneNumber(phone) 
            ? "Cell phone number successfully captured." 
            : "Cell phone number incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        
        if (userCheck.contains("successfully") && passCheck.contains("successfully") && phoneCheck.contains("successfully")) {
            this.username = name;
            this.password = pass;
            this.phoneNumber = phone;
            return "Registration successful!";
        }
        
        return userCheck + "\n" + passCheck + "\n" + phoneCheck;
    }

    
     //Validates login credentials against stored values.
     //return true if match
     
    public boolean loginUser(String username, String password) {
        return this.username != null && this.username.equals(username) && this.password.equals(password);
    }

   
     // Returns login status message.
    //return welcome or error message
    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + this.username + ", it is great to see you again."; // Fixed: use this.username
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Getters if needed for testing
    public String getUsername() { return username; }
    public String getPhoneNumber() { return phoneNumber; }
}
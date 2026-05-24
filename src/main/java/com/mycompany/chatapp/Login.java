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
    
    //These variables store the details of the user
    //Once a user registers, their data is saved here.
    
    String username;
    String password;
    String phoneNumber;

    // Username validation
    // Must contain "_"
    // Must be 5 characters or less

    public boolean checkUserName(String username) {

        return username.contains("_") && username.length() <= 5;
    }

    //Checking password complexity
    //Password requirements:
    //Be at least 8 characters long
    //Contains at least 1 capital letter
    //Contains at least 1 number 
    //Contains at least 1 special character

    public boolean checkPasswordComplexity(String password) {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {

            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {

                hasCapital = true;

            } else if (Character.isDigit(c)) {

                hasNumber = true;

            } else if (!Character.isLetterOrDigit(c)) {

                hasSpecial = true;
            }
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

         //Checking cellphone number validation
        //Checking if it: 
        //Starts with +27 
        //Is no more than 12 characters long

    public boolean checkCellPhoneNumber(String phoneNumber) {

        return phoneNumber.startsWith("+27")
                && phoneNumber.length() <= 12;
    }

         // Checking:
        //The username
        //The password
        //The phone number
        //Store data if everything is correct
        //Returns specific messeges

    public String registerUser(
            String username,
            String password,
            String phoneNumber) {

        if (!checkUserName(username)) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {

            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {

            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // Save details
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;

        return "User registered successfully.";
    }

    //Allowing the user to log in the same details they registered with
    public boolean loginUser(String username, String password) {

        return this.username.equals(username)
                && this.password.equals(password);
    }

      //Returning login status

    public String returnLoginStatus(boolean success) {

        if (success) {

            return "Welcome "
                    + username
                    + " it is great to see you again.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }
}
    

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {

        //Scanner allows the user to enter information
        Scanner input = new Scanner(System.in);

        // Create Login object
        Login login = new Login();

        // ================= REGISTER =================

        System.out.println("=== USER REGISTRATION ===");

        System.out.print("Enter a username: ");
        String username = input.nextLine();

        System.out.print("Enter a password: ");
        String password = input.nextLine();

        System.out.print("Enter your South African phone number (+27...): ");
        String phoneNumber = input.nextLine();

        //Call the registerUser method and store the message it returns
        String response= login.registerUser(username,password,phoneNumber);

        //Show the registration message
        System.out.println(response);

        // ================= LOGIN SECTION =================

        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        //Call loginUser to check if details match the stored ones
        boolean loggedIn = login.loginUser(loginUsername,loginPassword);

        System.out.println(login.returnLoginStatus(loggedIn));
    }
}
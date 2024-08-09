/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.utils;

/**
 *
 * @author ADMIN
 */
public class UserSession {
     private static String loggedInUsername;

    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
}

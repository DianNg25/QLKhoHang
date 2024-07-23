package com.inventory.utils;

import com.inventory.entity.Employees;
import com.inventory.main.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Auth {

    /**
     * Holds the information of the logged-in user.
     */
    public static Employees user = null;

    /**
     * Clears user information on logout.
     */
    public static void clear() {
        Auth.user = null;
    }

    /**
     * Checks if a user is logged in.
     */
    public static boolean isLoggedIn() {
        return Auth.user != null;
    }

    public static boolean isAdmin() {
        if (user == null) {
            return false; // Not logged in, so not an admin
        }

        String position = user.getPosition(); // Get position directly from the user object

        return position.equalsIgnoreCase("Admin"); // Case-insensitive comparison
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.utils;

/**
 *
 * @author ADMIN
 */
public class StringUtils {
    public static String trimChar(char[] charArray) {
        int start = 0, end = charArray.length - 1;
        while (start <= end && charArray[start] == ' ') start++;
        while (end >= start && charArray[end] == ' ') end--;
        return new String(charArray, start, end - start + 1);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.log;

/**
 *
 * @author ADMIN
 */
public class ShippingFee {
//     private static final int NEAR_FEE = 25; // Giá trung bình cho khoảng cách gần
//    private static final int FAR_FEE = 55;  // Giá trung bình cho khoảng cách xa
//
//    public static int calculateFee(int distance) {
//        if (distance <= 100) {
//            return NEAR_FEE;
//        } else {
//            return FAR_FEE;
//        }
//    }
    
    
       public static int calculateFee(int distance) {
        // Giả định phí vận chuyển là 1000 đồng/km
        return distance * 100;
    }
}

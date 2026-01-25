package com.upb.agripos.view;

public class ConsoleView {
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayProduct(String id, String name) {
        System.out.println("Product ID: " + id);
        System.out.println("Product Name: " + name);
    }
}
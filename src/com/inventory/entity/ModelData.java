package com.inventory.entity;

public class ModelData {
    private String month;
    private double imported;
    private double exported;
    private double revenue;
    private double endingInventory;

    // Constructor
    public ModelData(String month, double imported, double exported, double revenue, double endingInventory) {
        this.month = month;
        this.imported = imported;
        this.exported = exported;
        this.revenue = revenue;
        this.endingInventory = endingInventory;
    }

    // Getters
    public String getMonth() {
        return month;
    }

    public double getImported() {
        return imported;
    }

    public double getExported() {
        return exported;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getEndingInventory() {
        return endingInventory;
    }

    // Setters
    public void setMonth(String month) {
        this.month = month;
    }

    public void setImported(double imported) {
        this.imported = imported;
    }

    public void setExported(double exported) {
        this.exported = exported;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setEndingInventory(double endingInventory) {
        this.endingInventory = endingInventory;
    }
}


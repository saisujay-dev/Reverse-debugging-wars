package com.nithigh.bites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;         // ✅ matches frontend field 'name'
    private String itemName;      // ✅ matches frontend field 'itemName'
    private int qty;              // ✅ matches frontend field 'qty'
    private double amount;        // ✅ matches frontend field 'amount'
    private String status;

    // ✅ Fixed: added no-args constructor required by JPA
    public FoodOrder() {}

    public FoodOrder(String name, String itemName, int qty, double amount, String status) {
        this.name = name;
        this.itemName = itemName;
        this.qty = qty;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getItemName() { return itemName; }
    public int getQty() { return qty; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setQty(int qty) { this.qty = qty; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setStatus(String status) { this.status = status; }
}
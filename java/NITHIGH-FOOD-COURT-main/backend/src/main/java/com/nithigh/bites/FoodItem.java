package com.nithigh.bites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private double price;
    private String imageUrl;
    private boolean available;

    // ✅ Fixed: added no-args constructor required by JPA
    public FoodItem() {}

    public FoodItem(String name, String category, double price, String imageUrl, boolean available) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.available = available;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public boolean isAvailable() { return available; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setAvailable(boolean available) { this.available = available; }
}
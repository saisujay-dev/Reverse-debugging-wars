package com.nithigh.bites;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {

    private final FoodItemRepository foodItemRepository;
    private final FoodOrderRepository foodOrderRepository;

    public FoodService(FoodItemRepository foodItemRepository, FoodOrderRepository foodOrderRepository) {
        this.foodItemRepository = foodItemRepository;
        this.foodOrderRepository = foodOrderRepository;
    }

    public List<FoodItem> getAllFoods() {
        return foodItemRepository.findAll();
    }

    public FoodItem addFood(FoodItem foodItem) {
        if (foodItem.getName() == null || foodItem.getName().isBlank()) {
            throw new IllegalArgumentException("Food name cannot be blank");
        }
        if (foodItem.getCategory() == null || foodItem.getCategory().isBlank()) {
            throw new IllegalArgumentException("Food category cannot be blank");
        }
        if (foodItem.getPrice() <= 0) {
            throw new IllegalArgumentException("Food price must be greater than 0");
        }
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> searchFoods(String q) {
        return foodItemRepository.findByNameContainingIgnoreCase(q);
    }

    public List<FoodItem> getFoodsByCategory(String category) {
        return foodItemRepository.findByCategoryContainingIgnoreCase(category);
    }

    public FoodOrder placeOrder(FoodOrder order) {
        if (order.getName() == null || order.getName().isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be blank");
        }
        if (order.getItemName() == null || order.getItemName().isBlank()) {
            throw new IllegalArgumentException("Order must have at least one food item");
        }
        if (order.getQty() <= 0) {
            throw new IllegalArgumentException("Order quantity must be greater than 0");
        }
        if (order.getAmount() < 0) {
            throw new IllegalArgumentException("Order amount cannot be negative");
        }
        // Default blank status to PLACED
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PLACED");
        }
        return foodOrderRepository.save(order);
    }

    public List<FoodOrder> getAllOrders() {
        return foodOrderRepository.findAll();
    }
}
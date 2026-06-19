package com.nithigh.bites;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public ResponseEntity<List<FoodItem>> getFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @PostMapping("/foods")
    public ResponseEntity<?> addFood(@RequestBody FoodItem foodItem) {
        try {
            return ResponseEntity.ok(foodService.addFood(foodItem));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/foods/search")
    public ResponseEntity<List<FoodItem>> searchFoods(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(foodService.searchFoods(keyword));
    }

    @GetMapping("/foods/category")
    public ResponseEntity<List<FoodItem>> getFoodsByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(foodService.getFoodsByCategory(category));
    }

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@RequestBody FoodOrder order) {
        try {
            return ResponseEntity.ok(foodService.placeOrder(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<FoodOrder>> getOrders() {
        return ResponseEntity.ok(foodService.getAllOrders());
    }
}
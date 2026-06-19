package com.nithigh.bites;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    // ✅ Fixed: field is 'name' not 'names'
    List<FoodItem> findByNameContainingIgnoreCase(String name);

    List<FoodItem> findByCategoryContainingIgnoreCase(String category);
}
package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// ✅ Fixed: added missing assertion imports
@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    void shouldAddFoodItem() {
        FoodItem item = new FoodItem("French Fries", "Snacks", 69, "image", true);

        FoodItem saved = foodService.addFood(item);

        // ✅ Fixed: assertEquals should check the actual saved name, not "Burger"
        assertEquals("French Fries", saved.getName());
        // ✅ Fixed: saved item should have an auto-generated ID (not null)
        assertNotNull(saved.getId());
    }

    @Test
    void shouldRejectBlankFoodName() {
        FoodItem item = new FoodItem("", "Snacks", 69, "image", true);

        // ✅ Fixed: service throws IllegalArgumentException, not NullPointerException
        assertThrows(IllegalArgumentException.class, () -> {
            foodService.addFood(item);
        });
    }
}
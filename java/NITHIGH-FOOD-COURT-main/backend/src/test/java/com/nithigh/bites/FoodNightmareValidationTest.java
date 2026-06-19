package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FoodNightmareValidationTest {

    @Autowired
    private FoodService foodService;

    @Test
    void nightmare_shouldRejectNullFoodName() {
        FoodItem item = new FoodItem(null, "Snacks", 50, "image", true);
        assertThrows(IllegalArgumentException.class, () -> foodService.addFood(item));
    }

    @Test
    void nightmare_shouldRejectBlankCategory() {
        // ✅ Note: FoodService currently only validates name & price.
        // Adding category validation to FoodService to make this test pass.
        FoodItem item = new FoodItem("Burger", "", 129, "image", true);
        assertThrows(IllegalArgumentException.class, () -> foodService.addFood(item));
    }

    @Test
    void nightmare_shouldRejectZeroPrice() {
        FoodItem item = new FoodItem("Free Pizza", "Pizza", 0, "image", true);
        assertThrows(IllegalArgumentException.class, () -> foodService.addFood(item));
    }

    @Test
    void nightmare_shouldAcceptSpecialCharacterFoodName() {
        FoodItem item = new FoodItem("Paneer-Roll 2.0", "Snacks", 99, "image", true);
        FoodItem saved = foodService.addFood(item);
        assertNotNull(saved.getId());
        assertEquals("Paneer-Roll 2.0", saved.getName());
    }

    @Test
    void nightmare_shouldRejectBlankCustomerName() {
        // ✅ Fixed: use correct field names matching FoodOrder constructor (name, itemName, qty, amount, status)
        FoodOrder order = new FoodOrder("", "Veg Pizza", 1, 199, "PLACED");
        assertThrows(IllegalArgumentException.class, () -> foodService.placeOrder(order));
    }

    @Test
    void nightmare_shouldRejectBlankOrderFoodName() {
        FoodOrder order = new FoodOrder("Sanjan", "", 1, 199, "PLACED");
        assertThrows(IllegalArgumentException.class, () -> foodService.placeOrder(order));
    }

    @Test
    void nightmare_shouldRejectNegativeTotalPrice() {
        FoodOrder order = new FoodOrder("Sanjan", "Veg Pizza", 1, -199, "PLACED");
        assertThrows(IllegalArgumentException.class, () -> foodService.placeOrder(order));
    }

    @Test
    void nightmare_shouldDefaultBlankOrderStatusToPlaced() {
        FoodOrder order = new FoodOrder("Sanjan", "Veg Pizza", 1, 199, "");
        FoodOrder saved = foodService.placeOrder(order);
        assertEquals("PLACED", saved.getStatus());
    }
}
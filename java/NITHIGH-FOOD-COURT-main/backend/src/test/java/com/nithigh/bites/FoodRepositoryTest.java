package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// ✅ Fixed: added missing assertion import
@DataJpaTest
public class FoodRepositoryTest {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Test
    void shouldSearchFoodByName() {
        foodItemRepository.save(new FoodItem("Chocolate Shake", "Beverages", 99, "image", true));

        // ✅ Fixed: correct method name findByNameContainingIgnoreCase (not findByFoodName...)
        List<FoodItem> result = foodItemRepository.findByNameContainingIgnoreCase("chocolate");

        // ✅ Fixed: should find 1 result, not 0
        assertEquals(1, result.size());
    }
}
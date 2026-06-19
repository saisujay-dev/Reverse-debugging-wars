package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// ✅ Fixed: added missing assertion imports
@SpringBootTest
public class FoodNightmareBrokenTest {

    @Test
    void nightmare_brokenMathStyleSanityTest() {
        // ✅ Fixed: 2 + 2 = 4, not 5
        assertEquals(4, 2 + 2);
    }

    @Test
    void nightmare_brokenStringExpectation() {
        String appName = "NitHigh Bites";
        // ✅ Fixed: correct project name
        assertEquals("NitHigh Bites", appName);
    }

    @Test
    void nightmare_brokenExceptionExpectation() {
        // ✅ Fixed: correct exception type — IllegalArgumentException, not NullPointerException
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Invalid input");
        });
    }
}
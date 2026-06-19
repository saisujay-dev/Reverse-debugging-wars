package com.nithigh.bites;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping("/api/health")
    public String health() {
        // ✅ Exact string expected by FoodNightmareApiTest
        return "NitHigh Bites backend is running \u2705";
    }
}
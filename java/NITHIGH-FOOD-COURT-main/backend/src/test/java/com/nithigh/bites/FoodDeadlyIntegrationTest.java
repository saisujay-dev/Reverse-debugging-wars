package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodDeadlyIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/api";
    }

    @Test
    void deadly_shouldRejectInvalidFoodPrice() {
        FoodItem item = new FoodItem("Bad Food", "Snacks", -10, "image", true);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl() + "/foods", item, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deadly_shouldRejectInvalidOrderQuantity() {
        FoodOrder order = new FoodOrder("Sanjan", "Pizza", 0, 0, "PLACED");

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl() + "/orders", order, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
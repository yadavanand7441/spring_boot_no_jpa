package com.spring.no.jpa.boot.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void testNotFoundHandler() {
        // Arrange
        ResourceNotFoundException ex = new ResourceNotFoundException("Employee not found");

        // Act
        ResponseEntity<Map<String, Object>> response = exceptionHandler.notFoundHandler(ex);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.NOT_FOUND, body.get("status"));
        assertEquals("Employee not found", body.get("message:"));
        assertEquals(false, body.get("success"));
    }
}

package com.spring.no.jpa.boot.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Employee not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testNoArgsConstructor() {
        ResourceNotFoundException exception = new ResourceNotFoundException();

        assertNotNull(exception);
        assertEquals("Resource not found !", exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }
}

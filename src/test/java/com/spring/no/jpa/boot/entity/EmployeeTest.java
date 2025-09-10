package com.spring.no.jpa.boot.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("John Doe");
        emp.setSalary(50000.0);
        emp.setAddress("New York");

        assertEquals(1, emp.getId());
        assertEquals("John Doe", emp.getName());
        assertEquals(50000.0, emp.getSalary());
        assertEquals("New York", emp.getAddress());
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        Employee emp = new Employee(2, "Jane Smith", 60000.0, "Los Angeles");

        assertEquals(2, emp.getId());
        assertEquals("Jane Smith", emp.getName());
        assertEquals(60000.0, emp.getSalary());
        assertEquals("Los Angeles", emp.getAddress());
    }

    @Test
    void testToString() {
        Employee emp = new Employee(3, "Alice", 70000.0, "Chicago");

        String expected = "Employee{id=3, name='Alice', salary=70000.0, address='Chicago'}";
        assertEquals(expected, emp.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Employee emp1 = new Employee(4, "Bob", 80000.0, "Miami");
        Employee emp2 = new Employee(4, "Bob", 80000.0, "Miami");

        // Default equals/hashCode not overridden, so should be false
        assertNotEquals(emp1, emp2);
        assertNotEquals(emp1.hashCode(), emp2.hashCode());
    }
}

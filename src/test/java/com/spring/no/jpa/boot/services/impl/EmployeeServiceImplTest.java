package com.spring.no.jpa.boot.services.impl;

import com.spring.no.jpa.boot.entity.Employee;
import com.spring.no.jpa.boot.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl service;

    @BeforeEach
    void setUp() {
        // Reinitialize service before each test
        service = new EmployeeServiceImpl();
    }

    @Test
    void testCreateEmployee() {
        Employee emp = new Employee(99999, "Test User", 12345.67, "Test City");
        Employee created = service.createEmployee(emp);

        assertNotNull(created);
        assertEquals("Test User", created.getName());
        assertTrue(service.getAllEmployee().contains(emp));
    }

    @Test
    void testGetEmployeeByIdSuccess() {
        Employee emp = service.getEmployeeByID(13212); // exists from static block
        assertNotNull(emp);
        assertEquals("Updated Name", emp.getName());
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> service.getEmployeeByID(11111));
    }

    @Test
    void testUpdateEmployeeSuccess() {
        Employee updated = new Employee(13212, "Updated Name", 50000.0, "Updated City");
        Employee result = service.updateEmployee(updated, 13212);

        assertEquals("Updated Name", result.getName());
        assertEquals("Updated City", result.getAddress());
        assertEquals(50000.0, result.getSalary());
    }

    @Test
    void testUpdateEmployeeNotFound() {
        Employee emp = new Employee(11111, "Does Not Exist", 0.0, "Nowhere");
        assertThrows(ResourceNotFoundException.class, () -> service.updateEmployee(emp, 11111));
    }

    @Test
    void testDeleteEmployeeNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> service.deleteEmployee(11111));
    }

    @Test
    void testGetAllEmployee() {
        List<Employee> employees = service.getAllEmployee();
        assertNotNull(employees);
        assertTrue(employees.size() >= 6); // initial static employees
    }

    @Test
    void testAddListOfEmployee() {
        List<Employee> newEmployees = Arrays.asList(
                new Employee(20001, "Emp1", 10000.0, "City1"),
                new Employee(20002, "Emp2", 20000.0, "City2")
        );

        List<Employee> result = service.addListOfEmployee(newEmployees);

        assertTrue(result.containsAll(newEmployees));
        assertTrue(service.getAllEmployee().containsAll(newEmployees));
    }
}

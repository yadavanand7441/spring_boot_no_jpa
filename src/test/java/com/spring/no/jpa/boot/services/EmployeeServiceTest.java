package com.spring.no.jpa.boot.services;

import com.spring.no.jpa.boot.entity.Employee;
import com.spring.no.jpa.boot.exceptions.ResourceNotFoundException;
import com.spring.no.jpa.boot.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        // Create a fresh service before each test
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void testCreateEmployee() {
        Employee newEmp = new Employee(99999, "Test User", 12345.67, "Test City");
        Employee created = employeeService.createEmployee(newEmp);

        assertNotNull(created);
        assertEquals("Test User", created.getName());
        assertTrue(employeeService.getAllEmployee().contains(newEmp));
    }

    @Test
    void testUpdateEmployeeSuccess() {
        Employee updatedInfo = new Employee(0, "Updated Name", 55555.55, "Updated City");
        Employee updated = employeeService.updateEmployee(updatedInfo, 13212);

        assertNotNull(updated);
        assertEquals("Updated Name", updated.getName());
        assertEquals("Updated City", updated.getAddress());
        assertEquals(55555.55, updated.getSalary());
    }

    @Test
    void testUpdateEmployeeNotFound() {
        Employee updatedInfo = new Employee(0, "Not Exist", 11111.11, "No City");

        assertThrows(ResourceNotFoundException.class, () ->
                employeeService.updateEmployee(updatedInfo, 99999));
    }

    @Test
    void testDeleteEmployeeSuccess() {
        Employee deleted = employeeService.deleteEmployee(52341);

        assertNotNull(deleted);
        assertEquals(52341, deleted.getId());
        assertFalse(employeeService.getAllEmployee().contains(deleted));
    }

    @Test
    void testDeleteEmployeeNotFound() {
        assertThrows(ResourceNotFoundException.class, () ->
                employeeService.deleteEmployee(88888));
    }

    @Test
    void testGetEmployeeByIdSuccess() {
        Employee emp = employeeService.getEmployeeByID(83623);

        assertNotNull(emp);
        assertEquals("Guriya Kumari", emp.getName());
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        assertThrows(ResourceNotFoundException.class, () ->
                employeeService.getEmployeeByID(11111));
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployee();

        assertNotNull(employees);
        assertTrue(employees.size() >= 6); // initial static list
    }

    @Test
    void testAddListOfEmployees() {
        List<Employee> newList = Arrays.asList(
                new Employee(77777, "New Emp 1", 1234.56, "City1"),
                new Employee(88888, "New Emp 2", 6543.21, "City2")
        );

        List<Employee> updatedList = employeeService.addListOfEmployee(newList);

        assertTrue(updatedList.containsAll(newList));
    }
}

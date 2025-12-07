package com.spring.no.jpa.boot.controller;

import com.spring.no.jpa.boot.entity.Employee;
import com.spring.no.jpa.boot.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerMainTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private ControllerMain controllerMain;

    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emp1 = new Employee();
        emp1.setId(1);
        emp1.setName("Anand");

        emp2 = new Employee();
        emp2.setId(2);
        emp2.setName("Rahul");
    }

    @Test
    void testGetAllEmployee() {
        when(employeeService.getAllEmployee()).thenReturn(Arrays.asList(emp1, emp2));

        ResponseEntity<List<Employee>> response = controllerMain.getAllEmployee();

        assertEquals(2, response.getBody().size());
        verify(employeeService, times(1)).getAllEmployee();
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.getEmployeeByID(1)).thenReturn(emp1);

        ResponseEntity<Employee> response = controllerMain.getEmployeeById(1);

        assertEquals("Anand", response.getBody().getName());
        verify(employeeService, times(1)).getEmployeeByID(1);
    }

    @Test
    void testDeleteEmployeeById() {
        when(employeeService.deleteEmployee(1)).thenReturn(emp1);

        ResponseEntity<Employee> response = controllerMain.deleteEmployeeById(1);

        assertEquals(1, response.getBody().getId());
        verify(employeeService, times(1)).deleteEmployee(1);
    }

    @Test
    void testUpdateEmployeeById() {
        when(employeeService.updateEmployee(emp1, 1)).thenReturn(emp1);

        ResponseEntity<Employee> response = controllerMain.updateEmployeeById(1, emp1);

        assertEquals("Anand", response.getBody().getName());
        verify(employeeService, times(1)).updateEmployee(emp1, 1);
    }

    @Test
    void testAddEmployee() {
        when(employeeService.createEmployee(emp1)).thenReturn(emp1);

        ResponseEntity<Employee> response = controllerMain.addEmployee(emp1);

        assertEquals("Anand", response.getBody().getName());
        verify(employeeService, times(1)).createEmployee(emp1);
    }

    @Test
    void testAddMultipleEmployee() {
        List<Employee> employees = Arrays.asList(emp1, emp2);
        when(employeeService.addListOfEmployee(employees)).thenReturn(employees);

        ResponseEntity<List<Employee>> response = controllerMain.addMultipleEmployee(employees);

        assertEquals(2, response.getBody().size());
        verify(employeeService, times(1)).addListOfEmployee(employees);
    }
}

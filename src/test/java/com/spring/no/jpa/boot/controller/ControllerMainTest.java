package com.spring.no.jpa.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.no.jpa.boot.entity.Employee;
import com.spring.no.jpa.boot.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControllerMain.class)
public class ControllerMainTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    void setUp() {
        emp1 = new Employee(1, "John", 50000.0, "Developer");
        emp2 = new Employee(2, "Jane", 70000.0, "Manager");
    }

    @Test
    void testGetAllEmployee() throws Exception {
        List<Employee> employees = Arrays.asList(emp1, emp2);
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employees);

        mockMvc.perform(get("/rest/api/no/jpa/empList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Mockito.when(employeeService.getEmployeeByID(1)).thenReturn(emp1);

        mockMvc.perform(get("/rest/api/no/jpa/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testDeleteEmployeeById() throws Exception {
        Mockito.when(employeeService.deleteEmployee(1)).thenReturn(emp1);

        mockMvc.perform(delete("/rest/api/no/jpa/{deleteId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testUpdateEmployeeById() throws Exception {
        Employee updated = new Employee(1, "John", 60000.0, "Lead Developer");
        Mockito.when(employeeService.updateEmployee(any(Employee.class), eq(1))).thenReturn(updated);

        mockMvc.perform(put("/rest/api/no/jpa/{updateId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Lead Developer"))  // ✅ fixed
                .andExpect(jsonPath("$.salary").value(60000.0));
    }


    @Test
    void testAddEmployee() throws Exception {
        Mockito.when(employeeService.createEmployee(any(Employee.class))).thenReturn(emp1);

        mockMvc.perform(post("/rest/api/no/jpa/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testAddMultipleEmployee() throws Exception {
        List<Employee> employees = Arrays.asList(emp1, emp2);
        Mockito.when(employeeService.addListOfEmployee(any(List.class))).thenReturn(employees);

        mockMvc.perform(post("/rest/api/no/jpa/saveAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"));
    }
}


package com.spring.no.jpa.boot.controller;

import com.spring.no.jpa.boot.entity.Employee;
import com.spring.no.jpa.boot.services.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/api/no/jpa")
public class ControllerMain {


    private Logger logger = LoggerFactory.getLogger(ControllerMain.class);

    private EmployeeServiceImpl employeeService;
    @Autowired
    public ControllerMain(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/empList")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> allEmployee = employeeService.getAllEmployee();
        logger.info("list of employee founded:{} ", allEmployee);
        return ResponseEntity.ok(allEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int employeeId) {
        Employee employeeByID = employeeService.getEmployeeByID(employeeId);
        logger.info("employee with given id is found:{} " , employeeId);
        return ResponseEntity.ok(employeeByID);
    }

    @DeleteMapping("/{deleteId}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("deleteId") int employeeId) {
        Employee employeeByID = employeeService.deleteEmployee(employeeId);
        logger.info("employee with given id is deleted:{} " , employeeByID);
        return ResponseEntity.ok(employeeByID);
    }

    @PutMapping("/{updateId}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("updateId") int employeeId, @RequestBody Employee employee) {
        Employee updateEmployeeByID = employeeService.updateEmployee(employee, employeeId);
        logger.info("employee with given id is updated:{} " , updateEmployeeByID);
        return ResponseEntity.ok(updateEmployeeByID);
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee addEmployee = employeeService.createEmployee(employee);
        logger.info("employee is added to DB:{} " , addEmployee);
        return ResponseEntity.ok(addEmployee);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Employee>> addMultipleEmployee(@RequestBody List<Employee> employees) {
        List<Employee> listOfEmployee = employeeService.addListOfEmployee(employees);
        logger.info("List of employee are added to DB:{} " , listOfEmployee);
        return ResponseEntity.status(200).body(listOfEmployee);
    }


}

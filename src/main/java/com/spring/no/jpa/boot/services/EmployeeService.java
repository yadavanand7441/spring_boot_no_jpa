package com.spring.no.jpa.boot.services;

import com.spring.no.jpa.boot.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee createEmployee(Employee employee);

    public Employee updateEmployee(Employee employee, int employeeId);

    public Employee deleteEmployee(int employeeId);

    public Employee getEmployeeByID(int employeeId);

    public List<Employee> getAllEmployee();

    public List<Employee> addListOfEmployee(List<Employee> employees);
}

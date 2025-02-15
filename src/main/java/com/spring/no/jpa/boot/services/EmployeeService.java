package com.spring.no.jpa.boot.services;

import com.spring.no.jpa.boot.entity.Employee;
import com.spring.no.jpa.boot.exceptions.ResourceNotFoundException;
import com.spring.no.jpa.boot.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeRepository {


    private static List<Employee>employeeList=new ArrayList<>();

    static {
        employeeList.add(new Employee(13212,"Anand Yadav",45000.45,"Muzaffarpur"));
        employeeList.add(new Employee(52341,"Monu Kumar",362423.45,"Mumbai"));
        employeeList.add(new Employee(83623,"Guriya Kumari",3272523.00,"Pune"));
        employeeList.add(new Employee(14223,"Suraj Chauhan",2723122.4623,"Kolkata"));
        employeeList.add(new Employee(37354,"Komal Bharti",92535232.356,"Delhi"));
        employeeList.add(new Employee(93343,"Pintu Pandey",82523.3634,"Bangalore"));
    }


    @Override
    public Employee createEmployee(Employee employee) {
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee,int employeeId) {
        Employee updateEmployee = employeeList.stream().filter(e -> e.getId() == employeeId).findFirst().orElseThrow(() ->
                new ResourceNotFoundException("you can not update the data because employee is not present with given id" + employeeId));
        updateEmployee.setName(employee.getName());
        updateEmployee.setAddress(employee.getAddress());
        updateEmployee.setSalary(employee.getSalary());
        return updateEmployee;
    }

    @Override
    public Employee deleteEmployee(int employeeId) {
        Employee employeeByIdDelete = employeeList.stream().filter(e -> e.getId() == employeeId).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("employee is not presented to delete with give id : " + employeeId));
        employeeList.remove(employeeByIdDelete);
        return employeeByIdDelete;
    }

    @Override
    public Employee getEmployeeByID(int employeeId) {
        Employee employeeById = employeeList.stream().filter(e -> e.getId() == employeeId).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("employee is not found with given id: " + employeeId));
        return employeeById;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeList;
    }

    @Override
    public List<Employee> addListOfEmployee(List<Employee>employees) {
        employeeList.addAll(employees);
        return employeeList;
    }
}

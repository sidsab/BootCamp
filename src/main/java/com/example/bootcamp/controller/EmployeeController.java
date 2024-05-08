package com.example.bootcamp.controller;

import com.example.bootcamp.model.Employee;
import com.example.bootcamp.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController (EmployeeService employeeService) {
        this.employeeService= employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees()
    {
        return employeeService.getAllEmployees();
    }
}

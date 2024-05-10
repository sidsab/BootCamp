package com.example.bootcamp.controller;

import com.example.bootcamp.model.Department;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController (final EmployeeServiceImpl employeeService) {
        this.employeeService= employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id)
    {
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id)
    {
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee)
    {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee)
    {
        return employeeService.updateEmployee(id,employee);
    }
}

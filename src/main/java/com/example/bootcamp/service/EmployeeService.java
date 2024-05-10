package com.example.bootcamp.service;

import com.example.bootcamp.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee findEmployeeById(Integer id);

    Employee updateEmployee(Integer id, Employee employee);

    void deleteEmployee(Integer id);
}

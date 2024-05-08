package com.example.bootcamp.service;

import com.example.bootcamp.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IEmployeeService {

    public Employee createEmployee(Employee employee);

    public List<Employee> getAllEmployees();

    public Employee findEmployeeById(Integer id);

    public Employee updateEmployee(Integer id, Employee employee);

    public void deleteEmployee(Integer id);
}

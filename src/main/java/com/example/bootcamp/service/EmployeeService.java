package com.example.bootcamp.service;

import com.example.bootcamp.model.Employee;
import com.example.bootcamp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository)
    {
        this.employeeRepository=employeeRepository;
    }
    @Override
    public Employee createEmployee(Employee employee) {
        Employee employeeSaved= employeeRepository.save(employee);
        return employeeSaved;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList= (List<Employee>) employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        Optional<Employee> employee= employeeRepository.findById(id);
        return employee.orElseThrow(()-> new RuntimeException("data not found"));
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployee(Integer id) {

    }
}

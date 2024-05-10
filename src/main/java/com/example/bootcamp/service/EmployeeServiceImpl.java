package com.example.bootcamp.service;

import com.example.bootcamp.exceptions.EmployeeNotFoundException;
import com.example.bootcamp.model.Department;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.repository.DepartmentRepository;
import com.example.bootcamp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository)
    {
        this.employeeRepository=employeeRepository;
        this.departmentRepository=departmentRepository;
    }
    @Override
    public Employee createEmployee(Employee employee) {
        Set<Department> mandatoryDepartments= departmentRepository.findDepartmentByMandatory(true);
        employee.getDepartments().addAll(mandatoryDepartments);
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
        return employee.orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee employeeToBeUpdated= employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found"));
        employeeToBeUpdated.setName(employee.getName());
        Employee employeeUpdated= employeeRepository.save(employeeToBeUpdated);
        return employeeUpdated;
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> employee= employeeRepository.findById(id);
        employee.orElseThrow(()->new EmployeeNotFoundException("Employee not found"));
        employeeRepository.deleteById(id);
    }
}

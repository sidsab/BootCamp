package com.example.bootcamp.service;

import com.example.bootcamp.model.Employee;
import com.example.bootcamp.repository.DepartmentRepository;
import com.example.bootcamp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @BeforeEach
    public void setup()
    {

    }

}

package com.example.bootcamp.service;

import com.example.bootcamp.model.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(Department department);
    List<Department> getAllDepartments();
    Department findDepartmentById(Integer id);
    Department updateDepartment(Integer id, Department department);
    void deleteDepartment(Integer id);
}

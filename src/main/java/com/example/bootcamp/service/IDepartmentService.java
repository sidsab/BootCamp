package com.example.bootcamp.service;

import com.example.bootcamp.model.Department;

import java.util.List;

public interface IDepartmentService {

    public Department createDepartment(Department department);
    public List<Department> getAllDepartments();
    public Department findDepartmentById(Integer id);
    public Department updateDepartment(Integer id, Department department);
    public void deleteDepartment(Integer id);
}

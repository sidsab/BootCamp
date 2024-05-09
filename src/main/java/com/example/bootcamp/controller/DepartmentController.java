package com.example.bootcamp.controller;

import com.example.bootcamp.model.Department;
import com.example.bootcamp.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService)
    {
        this.departmentService= departmentService;
    }

    @GetMapping("/departments")
    public List<Department> getDepartments()
    {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/addDepartment")
    public Department addDepartment(@RequestBody Department department)
    {
        return departmentService.createDepartment(department);
    }
}

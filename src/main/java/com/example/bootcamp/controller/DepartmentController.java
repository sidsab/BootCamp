package com.example.bootcamp.controller;

import com.example.bootcamp.model.Department;
import com.example.bootcamp.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(final DepartmentService departmentService)
    {
        this.departmentService= departmentService;
    }

    @GetMapping("/departments")
    public List<Department> getDepartments()
    {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable("id") Integer id)
    {
        return departmentService.findDepartmentById(id);
    }

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department)
    {
        return departmentService.createDepartment(department);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable("id") Integer id)
    {
        departmentService.deleteDepartment(id);
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Integer id, @RequestBody Department department)
    {
        return departmentService.updateDepartment(id,department);
    }
}

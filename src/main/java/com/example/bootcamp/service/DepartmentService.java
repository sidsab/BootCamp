package com.example.bootcamp.service;

import com.example.bootcamp.model.Department;
import com.example.bootcamp.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService{

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository)
    {
        this.departmentRepository= departmentRepository;
    }
    @Override
    public Department createDepartment(Department department) {
        Department departmentCreated= departmentRepository.save(department);
        return departmentCreated;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departmentsList= (List<Department>) departmentRepository.findAll();
        return departmentsList;
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Optional<Department> department= departmentRepository.findById(id);
        return department.orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public Department updateDepartment(Integer id, Department department) {
        Optional<Department> departmentToBeUpdated= departmentRepository.findById(id);
        departmentToBeUpdated.orElseThrow(()->new RuntimeException("Department not found"));
        Department departmentUpdated= departmentRepository.save(department);
        return departmentUpdated;
    }

    @Override
    public void deleteDepartment(Integer id) {
        Optional<Department> department= departmentRepository.findById(id);
        department.orElseThrow(()->new RuntimeException("Department not found"));
        departmentRepository.deleteById(id);
    }
}

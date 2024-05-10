package com.example.bootcamp.service;

import com.example.bootcamp.exceptions.DepartmentIsReadOnlyException;
import com.example.bootcamp.exceptions.DepartmentNotFoundException;
import com.example.bootcamp.model.Department;
import com.example.bootcamp.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        Department departmentCreated = departmentRepository.save(department);
        return departmentCreated;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departmentsList = departmentRepository.findAll();
        return departmentsList;
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
    }

    @Override
    public Department updateDepartment(Integer id, Department department) {
        Department departmentToBeUpdated = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        if (departmentToBeUpdated.getReadOnly() && department.getReadOnly()) {
            throw new DepartmentIsReadOnlyException("Department is readonly");
        }
        departmentToBeUpdated.setName(department.getName());
        departmentToBeUpdated.setMandatory(department.getMandatory());
        departmentToBeUpdated.setReadOnly(department.getReadOnly());
        departmentToBeUpdated.setEmployees(departmentToBeUpdated.getEmployees());
        Department departmentSaved = departmentRepository.save(departmentToBeUpdated);
        return departmentSaved;
    }

    @Override
    public void deleteDepartment(Integer id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        if (department.getReadOnly()) {
            throw new DepartmentIsReadOnlyException("Department is readonly");
        }
        departmentRepository.deleteById(id);
    }

    @PostConstruct
    public void doInit() {
        // do stuff
    }
}

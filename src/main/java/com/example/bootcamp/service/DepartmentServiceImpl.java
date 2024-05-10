package com.example.bootcamp.service;

import com.example.bootcamp.exceptions.DepartmentIsReadOnlyException;
import com.example.bootcamp.exceptions.DepartmentNotFoundException;
import com.example.bootcamp.model.Department;
import com.example.bootcamp.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository departmentRepository;
    public DepartmentServiceImpl(DepartmentRepository departmentRepository)
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
        return department.orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
    }

    @Override
    public Department updateDepartment(Integer id, Department department) {
        Department departmentToBeUpdated= departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        if(departmentToBeUpdated.getRead_only() && department.getRead_only())
        {
            throw new DepartmentIsReadOnlyException("Department is readonly");
        }
        departmentToBeUpdated.setName(department.getName());
        departmentToBeUpdated.setMandatory(department.getMandatory());
        departmentToBeUpdated.setRead_only(department.getRead_only());
        departmentToBeUpdated.setEmployees(departmentToBeUpdated.getEmployees());
        Department departmentSaved= departmentRepository.save(departmentToBeUpdated);
        return departmentSaved;
    }

    @Override
    public void deleteDepartment(Integer id) {
        Department department= departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        if(department.getRead_only())
        {
            throw new DepartmentIsReadOnlyException("Department is readonly");
        }
        departmentRepository.deleteById(id);
    }
}

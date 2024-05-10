package com.example.bootcamp.repository;

import com.example.bootcamp.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    Set<Department> findDepartmentByMandatory(Boolean isMandatory);
    List<Department> findAll();
}

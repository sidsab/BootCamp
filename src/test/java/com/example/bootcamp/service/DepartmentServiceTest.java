package com.example.bootcamp.service;

import com.example.bootcamp.model.Department;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.repository.DepartmentRepository;
import com.example.bootcamp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;
    private DepartmentService departmentService;
    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @BeforeEach
    public void setup()
    {
        departmentService= new DepartmentServiceImpl(departmentRepository);
    }

    @Test
    public void createDepartmentTest()
    {
        Department department= new Department();
        department.setName("Finance");
        department.setRead_only(true);
        department.setMandatory(true);
        when(departmentRepository.save(department)).thenReturn(department);
        Department departmentSaved= departmentService.createDepartment(department);
        assertThat(departmentSaved).isNotNull();
        assertEquals(departmentSaved,department);
    }

    @Test
    public void getAllDepartmentsTest()
    {
        //List<Department> departmentslList= departmentService.getAllDepartments();
        //assertThat(departmentslList).isNotNull();
    }

    @Test
    public void deleteDepartmentTest()
    {
//        int id=2;
//        departmentService.deleteDepartment(id);
//        verify(departmentRepository,times(1)).deleteById(id);
    }
}

package com.example.bootcamp.service;

import com.example.bootcamp.model.Department;
import com.example.bootcamp.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    public static final String DEPARTMENT_NAME_EXISTING = "Finance";
    public static final int DEPARTMENT_ID_EXISTING = 1;
    public static final int DEPARTMENT_ID_NEW = 5;
    public static final String DEPARTMENT_NAME_NEW = "Marketing";
    @Mock
    private DepartmentRepository mockDepartmentRepository;

    private DepartmentService ref;
    @InjectMocks
    private DepartmentServiceImpl concreteRef;


    private Department department01;
    private Department department02;
    private Department departmentExisting;
    private Department departmentReadOnly;
    private Department departmentWritable;
    private Department departmentMandatory;
    private Department departmentOptional;
    private Department departmentNew;

    @BeforeEach
    public void setup() {
        ref = new DepartmentServiceImpl(mockDepartmentRepository);

        department01 = Department.builder().id(DEPARTMENT_ID_EXISTING).name(DEPARTMENT_NAME_EXISTING).readOnly(true).mandatory(true).build();
        departmentExisting = department01;
        departmentReadOnly = department01;
        departmentMandatory = department01;
        department02 = Department.builder().id(DEPARTMENT_ID_NEW).name(DEPARTMENT_NAME_NEW).readOnly(true).mandatory(true).build();
        departmentNew = department02;
        departmentWritable = department02;
        departmentOptional = department02;

        lenient().when(mockDepartmentRepository.findById(DEPARTMENT_ID_EXISTING)).thenReturn(Optional.ofNullable(departmentExisting));
        lenient().when(mockDepartmentRepository.findById(DEPARTMENT_ID_NEW)).thenReturn(Optional.empty());
        lenient().when(mockDepartmentRepository.save(departmentExisting)).thenThrow(new Exception("Write a proper exception"));
        lenient().when(mockDepartmentRepository.save(departmentNew)).thenReturn(departmentNew);
    }

    @AfterEach
    void tearDown() {
        concreteRef = null;
        ref = null;

        department01 = null;
        department02 = null;
    }

    public void testinit() {
        concreteRef.doInit();
        // do assertions
    }

    //test<Method>_testIntention_specialInfo_expectedOutcome

    // testCreateDepartment_requestValid_success
    // testCreateDepartment_requestValid_databaseAbsent_dbException
    // testCreateDepartment_requestValid_departmentNameExists_uniqueViolation
    @Test
    public void createDepartmentTest_newDepartment_noConflicts_success() {
        when(mockDepartmentRepository.save(departmentExisting)).thenReturn(departmentExisting);
        Department departmentSaved = ref.createDepartment(departmentExisting);
        assertThat(departmentSaved).isNotNull();
        assertEquals(departmentSaved, departmentExisting);

        departmentExisting.setReadOnly(false);

        ref.findDepartmentById(DEPARTMENT_ID_NEW);
    }


    @Test
    public void getAllDepartmentsTest() {
        //List<Department> departmentslList= departmentService.getAllDepartments();
        //assertThat(departmentslList).isNotNull();
    }

    @Test
    public void deleteDepartmentTest() {
//        int id=2;
//        departmentService.deleteDepartment(id);
//        verify(departmentRepository,times(1)).deleteById(id);
    }
}

package com.example.bootcamp.service;

import com.example.bootcamp.exceptions.DepartmentIsReadOnlyException;
import com.example.bootcamp.exceptions.DepartmentNotFoundException;
import com.example.bootcamp.model.Department;
import com.example.bootcamp.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    public final int DEPARTMENT_ID_EXISTING = 1;
    public final String DEPARTMENT_NAME_EXISTING = "Finance";
    public final int DEPARTMENT_ID_NEW = 5;
    public final String DEPARTMENT_NAME_NEW = "Marketing";
    public final int DEPARTMENT_ID_IT = 2;
    public final String DEPARTMENT_NAME_IT = "IT";
    @Mock
    private DepartmentRepository mockDepartmentRepository;
    private DepartmentService ref;
    @InjectMocks
    private DepartmentServiceImpl concreteRef;
    private Department department01;
    private Department department02;
    private Department department03;
    private Department departmentExisting;
    private Department departmentReadOnly;
    private Department departmentNew;

    @BeforeEach
    public void setup() {
        ref = concreteRef;

        department01 = Department.builder().id(DEPARTMENT_ID_EXISTING).name(DEPARTMENT_NAME_EXISTING).readOnly(true).mandatory(true).build();
        departmentExisting = department01;
        departmentReadOnly = department01;
        department02 = Department.builder().id(DEPARTMENT_ID_NEW).name(DEPARTMENT_NAME_NEW).readOnly(true).mandatory(true).build();
        departmentNew = department02;
        department03 = Department.builder().id(DEPARTMENT_ID_IT).name(DEPARTMENT_NAME_IT).build();

        lenient().when(mockDepartmentRepository.findById(DEPARTMENT_ID_EXISTING)).thenReturn(Optional.ofNullable(departmentExisting));
        lenient().when(mockDepartmentRepository.findById(DEPARTMENT_ID_NEW)).thenReturn(Optional.empty());
        lenient().when(mockDepartmentRepository.save(departmentExisting)).thenReturn(departmentExisting);
        lenient().when(mockDepartmentRepository.save(departmentNew)).thenReturn(departmentNew);
    }

    @Test
    public void createDepartmentTest_newDepartment_noConflicts_success() {
        Department departmentSaved = concreteRef.createDepartment(departmentNew);
        assertThat(departmentSaved).isNotNull();
        assertEquals(departmentSaved, departmentNew);
    }

    @Test
    public void findDepartmentByIdTest_existingDepartment_noConflicts_success()
    {
        Department department= ref.findDepartmentById(departmentExisting.getId());
        assertThat(department).isNotNull();
    }

    @Test
    public void findDepartmentByIdTest_nonExistingDepartment_conflict_departmentReadOnlyException()
    {
        assertThrows(DepartmentNotFoundException.class,()->{
            ref.findDepartmentById(departmentNew.getId());
        });
    }


    @Test
    public void deleteDepartmentTest_nonReadOnlyDepartment_noConflict_success()
    {
        departmentExisting.setReadOnly(false);
        ref.deleteDepartment(departmentExisting.getId());
        verify(mockDepartmentRepository,times(1)).deleteById(departmentExisting.getId());
    }

    @Test
    public void deleteDepartmentTest_readOnlyDepartment_conflict_departmentReadOnlyException()
    {
        assertThrows(DepartmentIsReadOnlyException.class,()->{
            ref.deleteDepartment(departmentReadOnly.getId());
        });
    }

    @Test
    public void deleteDepartmentTest_nonExistingDepartment_conflict_departmentNotFoundException()
    {
        assertThrows(DepartmentNotFoundException.class,()->{
            ref.deleteDepartment(departmentNew.getId());
        });
    }

    @Test
    public void updateDepartmentTest_nonReadOnlyDepartment_noConflict_success()
    {
        departmentExisting.setReadOnly(false);
        departmentExisting.setName(DEPARTMENT_NAME_NEW);
        Department department= ref.updateDepartment(departmentExisting.getId(),departmentExisting);
        assertEquals(department, departmentExisting);
    }

    @Test
    public void updateDepartmentTest_readOnlyDepartment_conflict_departmentReadOnlyException()
    {
        assertThrows(DepartmentIsReadOnlyException.class,()->{
            ref.updateDepartment(departmentExisting.getId(),departmentExisting);
        });
    }

    @Test
    public void updateDepartmentTest_nonExistingDepartment_conflict_departmentNotFoundException()
    {
        assertThrows(DepartmentNotFoundException.class,()->{
            ref.updateDepartment(departmentNew.getId(),departmentNew);
        });
    }

    @Test
    public void getAllDepartmentsTest() {
        List<Department> departmentList = List.of(department02, department03);
        when(mockDepartmentRepository.findAll()).thenReturn(departmentList);

        List<Department> allDepartments = ref.getAllDepartments();
        assertThat(allDepartments).isEqualTo(departmentList);
    }

    @AfterEach
    void tearDown() {
        concreteRef = null;
        ref = null;
        department01 = null;
        department02 = null;
    }

}

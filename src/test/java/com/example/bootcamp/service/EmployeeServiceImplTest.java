package com.example.bootcamp.service;

import com.example.bootcamp.exceptions.DepartmentNotFoundException;
import com.example.bootcamp.exceptions.EmployeeNotFoundException;
import com.example.bootcamp.model.Department;
import com.example.bootcamp.model.Employee;
import com.example.bootcamp.repository.DepartmentRepository;
import com.example.bootcamp.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    public final String EMPLOYEE_NAME_EXISTING_ONE= "Siddharth";
    public final Integer EMPLOYEE_ID_EXISTING_ONE= 1;

    public final String EMPLOYEE_NAME_EXISTING_TWO= "Aditya";
    public final Integer EMPLOYEE_ID_EXISTING_TWO= 2;

    public final String EMPLOYEE_NAME_NEW= "Amit";

    public final Integer EMPLOYEE_ID_NEW= 5;

    public final String DEPARTMENT_NAME_MANDATORY = "Organisation";
    public final int DEPARTMENT_ID_MANDATORY = 1;

    public Employee employeeExisting;

    public Employee employeeExistingTwo;

    public Employee employeeNew;

    public Department departmentMandatory;

    @Mock
    private EmployeeRepository mockEmployeeRepository;

    @Mock
    private DepartmentRepository mockDepartmentRepository;
    private EmployeeService ref;
    @InjectMocks
    private EmployeeServiceImpl concreteRef;

    @BeforeEach
    public void setup()
    {
        ref= concreteRef;
        employeeExisting= Employee.builder().id(EMPLOYEE_ID_EXISTING_ONE).name(EMPLOYEE_NAME_EXISTING_ONE).build();
        employeeNew= Employee.builder().id(EMPLOYEE_ID_NEW).name(EMPLOYEE_NAME_NEW).build();
        employeeExistingTwo= Employee.builder().id(EMPLOYEE_ID_EXISTING_TWO).name(EMPLOYEE_NAME_EXISTING_TWO).build();
        departmentMandatory= Department.builder().id(DEPARTMENT_ID_MANDATORY).name(DEPARTMENT_NAME_MANDATORY).mandatory(true).readOnly(true).build();
        lenient().when(mockEmployeeRepository.findById(EMPLOYEE_ID_EXISTING_ONE)).thenReturn(Optional.ofNullable(employeeExisting));
        lenient().when(mockEmployeeRepository.findById(EMPLOYEE_ID_NEW)).thenReturn(Optional.empty());
        lenient().when(mockEmployeeRepository.save(employeeNew)).thenReturn(employeeNew);
        lenient().when(mockEmployeeRepository.save(employeeExisting)).thenReturn(employeeExisting);
    }

    @Test
    public void createEmployeeTest_newEmployee_noConflict_success()
    {
        Set<Department> mandatoryDepartmentList= new HashSet<Department>();
        mandatoryDepartmentList.add(departmentMandatory);
        when(mockDepartmentRepository.findDepartmentByMandatory(true)).thenReturn(mandatoryDepartmentList);
        employeeNew.setDepartments(mandatoryDepartmentList);
        Employee employeeSaved= ref.createEmployee(employeeNew);
        assertThat(employeeSaved).isNotNull();
        assertEquals(employeeSaved,employeeNew);
    }

    @Test
    public void deleteEmployeeTest_existingEmployee_noConflict_success()
    {
        ref.deleteEmployee(EMPLOYEE_ID_EXISTING_ONE);
        verify(mockEmployeeRepository,times(1)).deleteById(EMPLOYEE_ID_EXISTING_ONE);
    }

    @Test
    public void deleteEmployeeTest_nonExistingEmployee_conflict_employeeNotFoundException()
    {
        assertThrows(EmployeeNotFoundException.class,()->{
            ref.deleteEmployee(employeeNew.getId());
        });
    }

    @Test
    public void findEmployeeByIdTest_existingEmployee_noConflict_success()
    {
        Employee employee= ref.findEmployeeById(employeeExisting.getId());
        assertThat(employee).isNotNull();
        assertEquals(employee,employeeExisting);
    }

    @Test
    public void findEmployeeByIdTest_nonExistingEmployee_conflict_employeeNotFoundException()
    {
        assertThrows(EmployeeNotFoundException.class,()->{
            ref.findEmployeeById(employeeNew.getId());
        });
    }

    @Test
    public void updateEmployeeTest_existingEmployee_noConflict_success()
    {
        employeeExisting.setName(EMPLOYEE_NAME_NEW);
        Employee employeeUpdated= ref.updateEmployee(EMPLOYEE_ID_EXISTING_ONE, employeeExisting);
        assertEquals(employeeUpdated, employeeExisting);
    }

    @Test
    public void updateEmployeeTest_nonExistingEmployee_conflict_employeeNotFoundException()
    {
        assertThrows(EmployeeNotFoundException.class,()->{
            ref.findEmployeeById(employeeNew.getId());
        });
    }

    @Test
    public void getAllEmployeesTest_noConflict_success()
    {
        List<Employee> employeeList = List.of(employeeExisting, employeeExistingTwo);
        when(mockEmployeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> allEmployees = ref.getAllEmployees();
        Assertions.assertThat(allEmployees).isEqualTo(employeeList);
    }

    @AfterEach
    void tearDown() {
        concreteRef = null;
        ref = null;
        employeeNew = null;
        employeeExisting = null;
    }

}

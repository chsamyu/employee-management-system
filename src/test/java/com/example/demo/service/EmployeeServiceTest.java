package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private static final Integer ID = 1;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService service;

    @Test
    public void testGetAllEmployees() {

        Employee employee = mockEmployee();
        List<Employee> employeeList = List.of(employee);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> response = service.allEmployee();
        assertEquals(employeeList, response);
    }

    @Test
    public void testAddNewEmployee() {
        Employee employee = mockEmployee();
        service.addNewEmployee(employee);
        verify(employeeRepository).save(any());
    }

    @Test
    public void testFindEmployeeById() {
        Employee employee = mockEmployee();
        Optional<Employee> foundEmployee = Optional.of(employee);
        when(employeeRepository.findById(any())).thenReturn(foundEmployee);
        Optional<Employee> response = service.findById(ID);
        assertEquals(foundEmployee, response);
    }

    @Test
    public void testDeleteEmployeeById() {
        service.deleteById(ID);
        verify(employeeRepository).deleteById(any());
    }


    private Employee mockEmployee() {
        Employee employee = new Employee();
        employee.setName("Adam");
        employee.setActive(true);
        employee.setSalary(1000.00);
        return employee;

    }
}

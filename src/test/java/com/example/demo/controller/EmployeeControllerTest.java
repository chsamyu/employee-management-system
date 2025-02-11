package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private static final Integer ID = 1;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController controller;

    @Test
    public void testGetAllEmployees() {

        Employee employee = mockEmployee();
        List<Employee> employeeList = List.of(employee);

        when(employeeService.allEmployee()).thenReturn(employeeList);
        ResponseEntity<List<Employee>> response = controller.allEmployee();
        List<Employee> result = response.getBody();
        assertEquals(employeeList, result);
    }

    @Test
    public void testAddNewEmployee() {
        Employee employee = mockEmployee();
        controller.addNewEmployee(employee);
        verify(employeeService).addNewEmployee(any());
    }

    @Test
    public void testFindEmployeeById() {
        Employee employee = mockEmployee();
        Optional<Employee> foundEmployee = Optional.of(employee);
        when(employeeService.findById(any())).thenReturn(foundEmployee);
        ResponseEntity<Optional<Employee>> response = controller.findEmployee(1);
        Optional<Employee> result = response.getBody();
        assertEquals(foundEmployee, result);
    }

    @Test
    public void testDeleteEmployeeById() {
        controller.deleteEmployee(ID);
        verify(employeeService).deleteById(any());
     }


    private Employee mockEmployee() {
        Employee employee = new Employee();
        employee.setName("Adam");
        employee.setActive(true);
        employee.setSalary(1000.00);
        return employee;

    }
}

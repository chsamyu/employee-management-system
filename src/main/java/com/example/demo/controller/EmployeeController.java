package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PutMapping
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
        log.info("Received request to add an employee : {}", employee);
        return ResponseEntity.ok(employeeService.addNewEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> allEmployee(){
        log.info("Received request to list all the employees");
        return ResponseEntity.ok(employeeService.allEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> findEmployee(@PathVariable Integer id) {
        log.info("Received request to find single employee by id : {}", id);
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEmployee(@PathVariable Integer id) {
        log.info("Received request to delete single employee by id : {}", id);
        employeeService.deleteById(id);
    }
}

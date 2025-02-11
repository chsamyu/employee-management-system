package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee addNewEmployee(Employee employee) {
        log.info("Adding employee to database : {}", employee);
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> allEmployee(){
        log.info("Fetching all employees from database");
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Integer id) {
        log.info("Finding single employee from the database by given id : {}", id);
        return employeeRepository.findById(id);
    }

    public void deleteById(Integer id) {
        log.info("Removing employee from database for the id : {}", id);
        employeeRepository.deleteById(id);
    }
}

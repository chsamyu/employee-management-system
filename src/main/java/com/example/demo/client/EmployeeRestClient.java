package com.example.demo.client;

import com.example.demo.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Slf4j
public class EmployeeRestClient {

    private final String url;
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    public EmployeeRestClient(String url, String userId, String password) {
        this.url = url;
        String credentials = userId + ":" + password;
        byte[] plainBytes = credentials.getBytes();
        byte[] base64Bytes = Base64.getEncoder().encode(plainBytes);
        String base64Credentials = new String(base64Bytes);
        headers.add("Authorization", "Basic " + base64Credentials);
    }

    public void addNewEmployee(Employee employee) {
        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
        restTemplate.exchange(url, HttpMethod.POST, request, Employee.class);
    }

    public Employee[] getAllEmployees() {
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Employee[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Employee[].class);
        return response.getBody();
    }

    public Employee getEmployeeById(Integer id) {
        String path = url + "/" + id;
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Employee> response = restTemplate.exchange(path, HttpMethod.GET, request, Employee.class);
        return response.getBody();
    }

    public void deleteEmployeeById(Integer id) {
        String path = url + "/" + id;
        HttpEntity<Void> request = new HttpEntity<>(headers);
        restTemplate.exchange(path, HttpMethod.DELETE, request, Void.class);
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/api/employees";
        EmployeeRestClient client = new EmployeeRestClient(url, "user", "password");

        // adding
        Employee employee = new Employee();
        employee.setName("Dave");
        employee.setActive(true);
        employee.setSalary(1000.00);
        client.addNewEmployee(employee);
        log.info("Added new employee : {}", employee);

        // listing
        Employee[] employees = client.getAllEmployees();
        log.info("Employees : {}", employees);

        // get by id
        int size = employees.length;
        Employee latest = employees[size - 1];
        Integer id = latest.getId();

        Employee selected = client.getEmployeeById(id);
        log.info("Selected : {}", selected);


        //delete by id
        client.deleteEmployeeById(id);

        //after deleting
        employees = client.getAllEmployees();
        log.info("After deleting Employees : {}", employees);
    }
}

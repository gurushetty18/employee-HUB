package com.Employee.employHub.controller;

import java.util.List;



import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.employHub.entity.Employee;
import com.Employee.employHub.service.EmployeeService;


@RestController
@RequestMapping("/employes")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public String createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/{email}")
    public Employee fetchByEmail(@PathVariable String email) {
        return employeeService.fetchById(email);
    }

    @GetMapping
    public List<Employee> fetchAll() {
        return employeeService.fetchAll();
    }

    @PutMapping("/{email}")
    public String updateEmployee(@PathVariable String email, @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(email, employee);
    }

    @DeleteMapping("/{email}")
    public String deleteEmployeeById(@PathVariable String email) {
        return employeeService.deleteEmployeeById(email);
    }
}

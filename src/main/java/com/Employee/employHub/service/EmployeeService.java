package com.Employee.employHub.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.Employee.employHub.Exception.EmployeeNotFoundException;
import com.Employee.employHub.entity.Employee;
import com.Employee.employHub.repository.EmployeeRepository;
@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Insert
    public String createEmployee(Employee employee) {
        if (employee.getEmail() == null || employee.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email must be provided as ID");
        }
        employeeRepository.save(employee);
        return "Employee record saved successfully";
    }

    // FindById
    public Employee fetchById(String email) {
        return employeeRepository.findById(email)
                .orElseThrow(() -> new EmployeeNotFoundException("employee with mail "+email+ " not found"));
    }

    // FindAll
    public List<Employee> fetchAll() {
        return employeeRepository.findAll();
    }

    // DeleteById
    public String deleteEmployeeById(String email) {
        employeeRepository.deleteById(email);
        return "Employee record deleted successfully";
    }

    // UpdateById
    public String updateEmployeeById(String email, Employee employee) {
        Employee employee2 = employeeRepository.findById(email)
               .orElseThrow(()->new EmployeeNotFoundException("employee with mail "+email+ " not found"));;

        employee2.setEmail(email); // keep same ID
        employee2.setName(employee.getName());
        employee2.setDepartment(employee.getDepartment()); // FIXED
        employee2.setSalary(employee.getSalary());

        employeeRepository.save(employee2);
        return "Record updated successfully";
    }
}

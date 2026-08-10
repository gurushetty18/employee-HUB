package com.Employee.employHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.employHub.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	

}

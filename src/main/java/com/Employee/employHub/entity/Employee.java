package com.Employee.employHub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

	@NotBlank
	@Id
	private String email;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String department;
	
	@Positive
	private long salary;
	
}

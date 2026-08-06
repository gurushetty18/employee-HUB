package com.Employee.employHub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "name field is required it cannot be empty")
	private String name;
	
	@Email(message = "enter valid email")
	@NotEmpty(message = "email is mandetory for registration")
	private String email;
	
	@Size(min = 6 , max = 12)
	private String password;
	
	private String role;
	
	private boolean varify;
}

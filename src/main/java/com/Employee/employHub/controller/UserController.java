package com.Employee.employHub.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.employHub.dto.RegisterRequest;
import com.Employee.employHub.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userservice;

	public UserController(UserService service) {
		this.userservice = service;
	}

	
	@PostMapping
	public Object Registration(@RequestBody RegisterRequest request) {
		return userservice.Registration(request);
		
	}
}

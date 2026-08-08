package com.Employee.employHub.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.employHub.dto.RegisterRequest;
import com.Employee.employHub.dto.VerifyOtpRequest;
import com.Employee.employHub.service.OtpVerifyService;
import com.Employee.employHub.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userservice;
	private final OtpVerifyService otpVerifyService;

	

	
	public UserController(UserService userservice, OtpVerifyService otpVerifyService) {
		this.userservice = userservice;
		this.otpVerifyService = otpVerifyService;
	}

	@PostMapping("/register")
	public Object Registration(@RequestBody RegisterRequest request) {
		return userservice.Registration(request);
		
	}
	
	@PostMapping("/verify")
	public String VerifyUser(@RequestBody VerifyOtpRequest otpRequest) {
		return otpVerifyService.VerifyUser(otpRequest);
	}
}

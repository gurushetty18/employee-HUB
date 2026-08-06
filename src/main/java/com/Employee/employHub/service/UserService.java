package com.Employee.employHub.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.Employee.employHub.dto.RegisterRequest;
import com.Employee.employHub.entity.User;
import com.Employee.employHub.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public Object Registration(RegisterRequest request) {
		Optional<User> op = repository.findByEmail(request.getEmail());
		if (op.isPresent()) {
			return"email is already registred";
			
		}
		else {
			User user = new User();
			user.setName(request.getName());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setRole("user");
			user.setVarify(false);
			return "otp sent to "+request.getEmail();
		}
		
		
	}
	
}

package com.Employee.employHub.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.Employee.employHub.dto.RegisterRequest;
import com.Employee.employHub.entity.User;
import com.Employee.employHub.repository.UserRepository;
import com.Employee.employHub.util.OtpGenerator;

@Service
public class UserService {

	private final UserRepository repository;
	private final EmailService emailService;

	public UserService(UserRepository repository, EmailService emailService) {
		this.repository = repository;
		this.emailService = emailService;
	}

	// user registration
	// UserService.java
	public Object Registration(RegisterRequest request) {
		Optional<User> op = repository.findByEmail(request.getEmail());
		if (op.isPresent()) {
			return "email is already registred";
		} else {
			User user = new User();
			user.setName(request.getName());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setRole("user");

			String otp = OtpGenerator.otpGenerate();
			user.setOtp(otp);
			user.setOtpExpire(LocalDateTime.now().plusMinutes(5));
			user.setVarify(false);

			// --- MISSING STEP: SAVE TO DATABASE ---
			repository.save(user);

			// Send Email
			emailService.SendMail(user.getEmail(), otp);

			return "otp sent to " + request.getEmail();
		}
	}

}

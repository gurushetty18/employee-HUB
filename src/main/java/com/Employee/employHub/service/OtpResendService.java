package com.Employee.employHub.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.Employee.employHub.entity.User;
import com.Employee.employHub.repository.UserRepository;

@Service
public class OtpResendService {

	private final EmailService emailService;
	private final UserRepository userRepository;
	
	public OtpResendService(EmailService emailService, UserRepository userRepository) {
		this.emailService = emailService;
		this.userRepository = userRepository;
	}
	
	public String resendOtp(String email) {
		Optional<User> op = userRepository.findByEmail(email);
		if (op.isEmpty() || !op.isPresent()) {
			return email + " not exited register first " ;
		}
		
		User user = op.get();
		
		Random  random = new Random();
		String otp = String.format("%06d",random.nextInt(999999) );
		user.setOtp(otp);
		user.setOtpExpire(LocalDateTime.now().plusMinutes(1));
		user.setVarify(false);
		
		userRepository.save(user);
		
		emailService.SendMail(email, otp);
		
		return "new otp sent to ur " + email;
	}
	
	
}

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
	private final JavaMailSender javaMailSender;
	
	
	

	

	public UserService(UserRepository repository, JavaMailSender javaMailSender) {
		this.repository = repository;
		this.javaMailSender = javaMailSender;
	}


	//user registration
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
			String otp = OtpGenerator.otpGenerate();
			user.setOtp(otp);
			
			user.setOtpExpire(LocalDateTime.now().plusMinutes(5));
			
			user.setVarify(false);
			return "otp sent to "+request.getEmail();
		}
		
		
		
		
	}
	
	
	 // OTP generator
    private String otpGeneration() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    // Send OTP email
    private void sendMail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Verification OTP");
        message.setText("Your OTP is: " + otp + "\nIt is valid for 5 minutes.");
        javaMailSender.send(message);
    }
    
   
	
	
}

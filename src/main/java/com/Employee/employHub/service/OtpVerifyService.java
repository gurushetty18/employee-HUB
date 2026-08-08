package com.Employee.employHub.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Employee.employHub.dto.VerifyOtpRequest;
import com.Employee.employHub.entity.User;
import com.Employee.employHub.repository.UserRepository;

@Service
public class OtpVerifyService {

	private final UserRepository userRepository;

	public OtpVerifyService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String name(VerifyOtpRequest otpRequest) {
		Optional<User> optional = userRepository.findByEmail(otpRequest.getEmail());
		if (optional.isPresent()) {
			User user = optional.get();

			if (!user.getOtp().equals(otpRequest.getOtp())) {
				return " invalid otp";
			}
			if (LocalDateTime.now().isAfter(user.getOtpExpire())) {
				return "otp expired";
			}
			
			else {
				user.setVarify(true);
				user.setOtp(null);
				user.setOtpExpire(null);
				userRepository.save(user);
				return "otp verified Successfully";
			}
		}
		else
			return "user not found with email "+otpRequest.getEmail();

	}
}

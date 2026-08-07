package com.Employee.employHub.util;

import java.util.Random;

public class OtpGenerator {

	public static String otpGenerate() {
		
		Random random = new Random();
		String otp = String.format("%06d", random.nextInt(999999));
		return otp;
	}
	
}

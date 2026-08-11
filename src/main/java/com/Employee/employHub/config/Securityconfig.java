package com.Employee.employHub.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableAutoConfiguration
public class Securityconfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		httpSecurity.
		csrf(csrf->csrf.disable()).
		authorizeHttpRequests(auth-> auth.
				requestMatchers("/users/register","/users/verify").permitAll().
				requestMatchers("/users/**").authenticated(). 
				anyRequest().authenticated()).
		httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
		
	}
	
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		
		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin@123")).roles("ADMIN").build();
		UserDetails user = User.withUsername("user").password(passwordEncoder.encode("user@123")).roles("USER").build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	
	

}

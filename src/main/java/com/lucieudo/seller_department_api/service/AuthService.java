package com.lucieudo.seller_department_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucieudo.seller_department_api.dto.AuthRequest;
import com.lucieudo.seller_department_api.dto.AuthResponse;
import com.lucieudo.seller_department_api.dto.RegisterRequest;
import com.lucieudo.seller_department_api.entity.User;
import com.lucieudo.seller_department_api.entity.User.Role;
import com.lucieudo.seller_department_api.exception.ResourceNotFoundException;
import com.lucieudo.seller_department_api.repository.UserRepository;
import com.lucieudo.seller_department_api.security.JwtService;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthResponse register(RegisterRequest request) {
		User user = new User(null, request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()),
				Role.USER);
		userRepository.save(user);
		String token = jwtService.generateToken(org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail()).password(user.getPassword()).roles(user.getRole().name()).build());
		return AuthResponse.builder()
				.token(token)
				.email(user.getEmail())
				.role(user.getRole().name())
				.build();
	}

	public AuthResponse login(AuthRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));
		String token = jwtService.generateToken(org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail()).password(user.getPassword()).roles(user.getRole().name()).build());
		return AuthResponse.builder()
				.token(token)
				.email(user.getEmail())
				.role(user.getRole().name())
				.build();
	}
}
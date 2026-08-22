package com.lucieudo.seller_department_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucieudo.seller_department_api.dto.AuthResponse;
import com.lucieudo.seller_department_api.dto.LoginRequest;
import com.lucieudo.seller_department_api.dto.RegisterRequest;
import com.lucieudo.seller_department_api.entity.User;
import com.lucieudo.seller_department_api.entity.User.Role;
import com.lucieudo.seller_department_api.repository.UserRepository;
import com.lucieudo.seller_department_api.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.USER);

		userRepository.save(user);

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
				.password(user.getPassword()).roles(user.getRole().name()).build();

		String token = jwtService.generateToken(userDetails);

		AuthResponse response = AuthResponse.builder().token(token).email(user.getEmail()).role(user.getRole().name())
				.build();

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
				.password(user.getPassword()).roles(user.getRole().name()).build();

		String token = jwtService.generateToken(userDetails);

		AuthResponse response = AuthResponse.builder().token(token).email(user.getEmail()).role(user.getRole().name())
				.build();

		return ResponseEntity.ok(response);
	}
}
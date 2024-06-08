package com.application.nutsBee.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.nutsBee.Entity.Role;
import com.application.nutsBee.Entity.User;
import com.application.nutsBee.config.JWTUtil;
import com.application.nutsBee.repository.UserRepository;
import com.application.nutsBee.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register/user")
	public ResponseEntity<Map<String, Object>> registerByCredentials(@Valid @RequestBody User user) throws Exception {

		if (userService.existsByEmail(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(Collections.singletonMap("message", "User already exists"));
		}
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		List<Role> role = new ArrayList<>();
		User userDTO = new User();
		userDTO.setRoles(role);
		userService.saveUser(user);
		String token = jwtUtil.generateToken(userDTO.getEmail());
		return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("Authorization","Bearer "+ token),
				HttpStatus.CREATED);
	}

	@PostMapping("/login/user")
	public ResponseEntity<Map<String, Object>> loginByEmailPassword(@Valid @RequestBody User credentials) {

		UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
				credentials.getEmail(), credentials.getPassword());
		try {
			authenticationManager.authenticate(authCredentials);
		} catch (Exception e) {
			if (!isValidCredentials(credentials)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(Collections.singletonMap("message", "Invalid credentials"));
			}
		}
		String token = jwtUtil.generateToken(credentials.getEmail());
		return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("Authorization","Bearer "+ token),
				HttpStatus.CREATED);
	}

	public boolean isValidCredentials(User credentials) {
		Optional<User> users = userRepository.findByEmail(credentials.getEmail());
		User user = users.get();
		if (user == null || !passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
			return false;
		}
		return true;
	}

}
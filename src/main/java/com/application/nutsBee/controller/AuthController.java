package com.application.nutsBee.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.nutsBee.Entity.Role;
import com.application.nutsBee.Entity.User;
import com.application.nutsBee.config.JWTUtil;
import com.application.nutsBee.repository.UserRepository;
import com.application.nutsBee.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
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

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> registerByCredentials(@Valid @RequestBody User user) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
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
		return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("Authorization","Bearer "+ token),responseHeaders,
				HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginByEmailPassword(@Valid @RequestBody User credentials) {
		HttpHeaders responseHeaders = new HttpHeaders();
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
		return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("Authorization","Bearer "+ token), responseHeaders,
				HttpStatus.OK);
	}

	public boolean isValidCredentials(User credentials) {
		Optional<User> users = userRepository.findByEmail(credentials.getEmail());
		User user = users.get();
		if (user == null || !passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
			return false;
		}
		return true;
	}
	
	@PostMapping("/forgotPassword")
    public ResponseEntity<Map<String, Object>> forgotPassword(@RequestBody User user) throws MessagingException {
		HttpHeaders responseHeaders = new HttpHeaders();
		String token = jwtUtil.generateToken(user.getEmail());
		Map<String, Object> forgotPasswordMessage = userService.processForgotPassword(user.getEmail(),token);
        return new ResponseEntity<>(forgotPasswordMessage,responseHeaders,HttpStatus.OK);
    }

    @PatchMapping("/resetPassword")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody User userDto) {
    	HttpHeaders responseHeaders = new HttpHeaders();
    	Map<String, Object> resetPasswordMessage = userService.resetPassword(userDto);
    	return new ResponseEntity<>(resetPasswordMessage,responseHeaders,HttpStatus.OK);
    }
    
	@GetMapping("/resendOtp")
	public ResponseEntity<Map<String, Object>> resendOtp(@RequestParam String email) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		Map<String, Object> otpMessage = userService.sendOtpEmail(email);
		return new ResponseEntity<>(otpMessage,responseHeaders,HttpStatus.OK);
	} 

}
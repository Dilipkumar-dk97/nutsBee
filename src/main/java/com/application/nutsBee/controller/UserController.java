package com.application.nutsBee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.application.nutsBee.Entity.Role;
import com.application.nutsBee.Entity.User;
import com.application.nutsBee.service.RoleService;
import com.application.nutsBee.service.UserService;

@RestController
@RequestMapping("/nutsBee")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/users")
	public ResponseEntity<User> getUser(@RequestParam (required = true) String email) {
		User user = userService.getUserByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PatchMapping("/users/{userId}")
	public ResponseEntity<User> patchUserById(@PathVariable Long userId, @RequestBody Map<String,String> userBody) {
		User user = userService.patchUserById(userId,userBody);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}/role")
	public ResponseEntity<List<Role>> getRole(@PathVariable Long userId) {
		List<Role> role = roleService.getRole(userId);
		if (role == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(role);
	}
}

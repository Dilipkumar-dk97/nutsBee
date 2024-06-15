package com.application.nutsBee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.nutsBee.Entity.Role;
import com.application.nutsBee.Entity.User;
import com.application.nutsBee.repository.RoleRepository;
import com.application.nutsBee.repository.UserRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public Role createRole(Role roles, Long userId) {
		return roleRepository.save(roles);
	}

	public List<Role> getRole(Long userId) {
		return roleRepository.findRoleByUserId(userId);
	}
}

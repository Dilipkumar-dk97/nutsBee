package com.application.nutsBee.service;
import com.application.nutsBee.Entity.User;
import com.application.nutsBee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

	public User getUserByEmail(String email) {
		 return userRepository.findByEmail(email)
	                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + email));
	}
}

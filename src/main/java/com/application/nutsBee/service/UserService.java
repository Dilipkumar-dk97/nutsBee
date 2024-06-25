package com.application.nutsBee.service;
import com.application.nutsBee.Entity.User;
import com.application.nutsBee.repository.UserRepository;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
    	user.setOtp(0);
    	user.setPhoneNumber(0L);
        return userRepository.save(user);
    }

    public User getUserById(Long userId) throws Exception{
        return userRepository.findById(userId).get();
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

	public User getUserByEmail(String email) {
		 return userRepository.findByEmail(email)
	                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + email));
	}
	
	public Map<String, Object> processForgotPassword(String email, String token) throws MessagingException {
		User user = getUserByEmail(email);
		Map<String, Object> message = new HashMap<>();
		if (user != null) {
			String otp = String.format("%06d", new Random().nextInt(999999));
			user.setOtp(Integer.parseInt(otp));
			saveUser(user);
			String subject = "Password Reset";
			String htmlBody = generateResetPasswordHtml(otp);
			emailService.sendEmail(email, subject, htmlBody);
		} else {
			message.put("message", "INVALID CREDENTIALS PLEASE ENTER VALID " + email);
			return message;
		}
		message.put("Authorization","Bearer "+ token);
		message.put("message", "Your Password Reset Link Sent To Your Mail");
		return message;
	}
    
	public Map<String, Object> resetPassword(User requestDto) {
		User user = getUserByEmail(requestDto.getEmail());
		Map<String, Object> resetPasswordMessage = new HashMap<>();
		if (user != null) {
			// Validate old and new passwords 
			if (passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
				resetPasswordMessage.put("message", "NEW PASSWORD CANNOT BE THE SAME AS OLD PASSWORD");
				return resetPasswordMessage;
			} 
			int storedOtp = user.getOtp();
			if(requestDto.getOtp() == storedOtp) {
				String encodedPass = passwordEncoder.encode(requestDto.getPassword());
				user.setPassword(encodedPass);
				user.setOtp(0);
			    saveUser(user);
			}else {
				resetPasswordMessage.put("message", "PLEASE ENTER CORRECT OTP");
				return resetPasswordMessage;
			}
		} else {
			resetPasswordMessage.put("message", "INVALID CREDENTIALS PLEASE ENTER VALID" + requestDto.getEmail());
			return resetPasswordMessage;
		}
		resetPasswordMessage.put("message", "Your Password Reset Completed");
		return resetPasswordMessage;
	}
	
	private String generateResetPasswordHtml(String otp) {
		String resetLink = "http://localhost:8080/reset-password";
		return "<!DOCTYPE html>" + "<html>" + "<head>" + "<title>Reset Password</title>" + "</head>" + "<body>"
				+ "<h1>Reset Password</h1>" + "<p>Hi,</p>"
				+ "<p>You are receiving this email because we received a password reset request for your account.</p>"
				+ "<p>Please use the following OTP to reset your password: <strong>" + otp + "</strong></p>"
				+ "<p>To reset your password, click the button below:</p>" + "<p><a href=\"" + resetLink
				+ "\">Reset Password</a></p>"
				+ "<p>If you're having trouble clicking the button, copy and paste the URL below into your web browser:</p>"
				+ "<p>" + resetLink + "</p>" + "<p>Thank you!</p>" + "</body>" + "</html>";
	}
	

	public Map<String, Object> sendOtpEmail(String email) throws Exception {
    	User user = getUserByEmail(email);
    	Map<String, Object> otpMessage = new HashMap<>();
        if (user != null) {
        	String otp = String.format("%06d", new Random().nextInt(999999));
			user.setOtp(Integer.parseInt(otp));
            saveUser(user);
            emailService.sendOtpEmail(email,Integer.parseInt(otp));
            otpMessage.put("message", "New OTP has been sent to your email");
            return otpMessage;
        } else {
        	otpMessage.put("message", "Invalid email");
            return otpMessage;
        }
    }

	public User patchUserById(Long userId, Map<String, String> data) throws Exception {
		User user = getUserById(userId);
		updateUser(user, data);
		return userRepository.save(user);
	}

	private void updateUser(User user, Map<String, String> data) {
		
		data.forEach((key, value) -> {
            switch (key) {
                case "phoneNumber":
                	user.setPhoneNumber(Long.parseLong(value.toString()));
                    break;
            }
        });
	}
}

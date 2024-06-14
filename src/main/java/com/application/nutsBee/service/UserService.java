package com.application.nutsBee.service;
import com.application.nutsBee.Entity.User;
import com.application.nutsBee.repository.UserRepository;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
	
	public String processForgotPassword(String email) throws MessagingException {
		User user = getUserByEmail(email);
		if (user != null) {
			String otp = String.format("%06d", new Random().nextInt(999999));
			user.setOtp(Integer.parseInt(otp));
			saveUser(user);
			String subject = "Password Reset";
			String htmlBody = generateResetPasswordHtml(otp);
			emailService.sendEmail(email, subject, htmlBody);
		} else {
			return "INVALID CREDENTIALS PLEASE ENTER VALID " + email;
		}
		return "Your Password Reset Link Sent To Your Mail";
	}
    
	public String resetPassword(User requestDto) {
		User user = getUserByEmail(requestDto.getEmail());
		if (user != null) {
			// Validate old and new passwords 
			if (passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
				return "NEW PASSWORD CANNOT BE THE SAME AS OLD PASSWORD";
			} 
			int storedOtp = user.getOtp();
			if(requestDto.getOtp() == storedOtp) {
				String encodedPass = passwordEncoder.encode(requestDto.getPassword());
				user.setPassword(encodedPass);
			    saveUser(user);
			}else {
				return "PLEASE ENTER CORRECT OTP";
			}
		} else {
			return "INVALID CREDENTIALS PLEASE ENTER VALID" + requestDto.getEmail();
		}
		return "Your Password Reset Completed";
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
	

	public String sendOtpEmail(String email) throws Exception {
    	User user = getUserByEmail(email);
        if (user != null) {
        	String otp = String.format("%06d", new Random().nextInt(999999));
			user.setOtp(Integer.parseInt(otp));
            saveUser(user);
            emailService.sendOtpEmail(email,Integer.parseInt(otp));
            return "New OTP has been sent to your email";
        } else {
            return "Invalid email";
        }
    }
}

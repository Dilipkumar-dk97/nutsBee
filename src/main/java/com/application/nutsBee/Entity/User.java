package com.application.nutsBee.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;	
    private String password;
    private Long phoneNumber;
    private String email;
	
    @OneToMany(targetEntity = Role.class ,cascade = CascadeType.ALL )
    @JoinColumn(name = "userId", referencedColumnName = "id")
	private List<Role> roles;
	 
    private String roleType;
    
    private int otp;
    @OneToMany(targetEntity = Cart.class ,cascade = CascadeType.ALL )
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<Cart> cartItems;
    @OneToMany(targetEntity = Address.class ,cascade = CascadeType.ALL )
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<Address> addresses;
}

package com.application.nutsBee.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="address")
public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String flatNo;
        private String street;
        private String pincode;
        private String area;
        private String city;
        private String state;

    	@JoinColumn(name = "user_id")
    	private Long userId;

}

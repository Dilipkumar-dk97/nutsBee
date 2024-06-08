package com.application.nutsBee.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name ="payment_id")
    private Long paymentId;
    @Column (name ="payment_method")
    private String paymentMethod;
    @Column (name ="card_type")
    private String cardType;
    @Column (name ="card_number")
    private String cardNumber;
    @Column (name ="expiry_date")
    private String expiryDate;
    @Column(name = "cvv")
    private String cvv;
//    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
//    private Order order;

}

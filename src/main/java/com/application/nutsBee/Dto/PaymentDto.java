package com.application.nutsBee.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long paymentId;
    private String paymentMethod;
    private String cardType;
    private String cardNumber;
    private String expiryDate;
    private String cvv;

}

package com.application.nutsBee.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.application.nutsBee.Entity.Payment;
import com.application.nutsBee.Entity.ShippingAddress;
import com.application.nutsBee.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private LocalDateTime orderDate;
    private double totalPrice;
    private double totalPriceIncTax;
    private double totalPriceExcTax;
    private String status;
    private List<OrderedItemDto> orderedItems ;
    private User user;
    private ShippingAddress shippingAddresses;
    private Payment payment;

}

package com.application.nutsBee.service.implementation;

import com.application.nutsBee.Dto.OrderedItemDto;
import com.application.nutsBee.Dto.PaymentDto;
import com.application.nutsBee.Dto.ShippingAddressDto;
import com.application.nutsBee.Entity.User;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.application.nutsBee.Entity.Order}
 */
@Value
public class OrderDto implements Serializable {
    String orderId;
    LocalDateTime orderDate;
    double totalPrice;
    double totalPriceIncTax;
    double totalPriceExcTax;
    String status;
    Set<OrderedItemDto> orderedItems;
    User user;
    Set<ShippingAddressDto> shippingAddresses;
    PaymentDto payment;
}
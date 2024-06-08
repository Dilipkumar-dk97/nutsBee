package com.application.nutsBee.service;

import com.application.nutsBee.Dto.OrderDto;
import com.application.nutsBee.Entity.Order;

public interface OrderService {
    Order createOrder(OrderDto orderDto);
}

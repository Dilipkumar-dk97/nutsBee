package com.application.nutsBee.service.implementation;

import com.application.nutsBee.Dto.OrderDto;
import com.application.nutsBee.Entity.Order;
import com.application.nutsBee.mapper.OrderMapper;
import com.application.nutsBee.repository.OrderRepositoty;
import com.application.nutsBee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService  {
    @Autowired
    private OrderRepositoty orderRepositoty;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order createOrder(OrderDto orderDto) {
//        Order order = OrderMapper.orderDtoToOrder(orderDto);
//        order = orderRepositoty.save(order);
//        Long orderIdNumber = order.getOrderId();
//        order.setOrderDate(order.getOrderDate().toLocalDate().toString());
//        return order;
        Order order = orderMapper.orderDtoToOrder(orderDto); // Using the mapper to convert OrderDto to Order
        order = orderRepositoty.save(order);
        // No need to convert order date to string here, assuming order date is already a LocalDate
        return order;
    }

}

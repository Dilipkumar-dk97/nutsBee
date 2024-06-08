package com.application.nutsBee.Dto;

import com.application.nutsBee.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItemDto {

    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private Double price;
    private Order order;

}

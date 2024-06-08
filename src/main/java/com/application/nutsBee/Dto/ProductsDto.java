package com.application.nutsBee.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
    private Long id;
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private double priceIncTax;
    private double priceExcTax;
}

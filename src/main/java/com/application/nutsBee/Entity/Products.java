package com.application.nutsBee.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column
    private double price;

    @Column(name = "price_inc_tax")
    private double priceIncTax;

    @Column(name = "price_exc_tax")
    private double priceExcTax;
}

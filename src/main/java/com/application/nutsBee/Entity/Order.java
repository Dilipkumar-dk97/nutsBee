package com.application.nutsBee.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")

public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private Long orderId;
//    @Column(name="order_date")
//    private LocalDateTime orderDate;
//    @Column(name="total_price")
//    private double totalPrice;
//    @Column(name="total_price_inc_tax")
//    private double totalPriceIncTax;
//    @Column(name="total_price_exc_tax")
//    private double totalPriceExcTax;
//    @Column(name="order_status")
//    private String status;
////    @Column(name="ordered_items")
////    private List<OrderedItem> orderedItems;
//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
//    @JoinColumn(name = "payment_id", nullable = false)
//    private Payment payment;
//    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
//    @JoinColumn(name = "shipping_address_id", nullable = false)
//    private ShippingAddress shippingAddress;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OrderBy("id")
//    private List<OrderedItem> orderedItems = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "total_price_inc_tax")
    private double totalPriceIncTax;

    @Column(name = "total_price_exc_tax")
    private double totalPriceExcTax;

    @Column(name = "order_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @OrderBy("id")
    private List<OrderedItem> orderedItems = new ArrayList<>();
}

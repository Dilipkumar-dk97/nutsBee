package com.application.nutsBee.repository;

import com.application.nutsBee.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoty extends JpaRepository<Order, Long> {
}

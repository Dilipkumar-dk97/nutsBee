package com.application.nutsBee.service;

import com.application.nutsBee.Entity.Cart;

public interface CartService {

	Cart addToCart(Long userId, Long productId);

}

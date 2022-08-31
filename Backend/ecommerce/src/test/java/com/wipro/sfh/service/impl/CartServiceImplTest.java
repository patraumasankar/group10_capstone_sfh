package com.wipro.sfh.service.impl;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.sfh.entity.Cart;
import com.wipro.sfh.service.ICartService;

@SpringBootTest
class CartServiceImplTest {

	@Autowired
	private ICartService cartService;
	
	@Test
	void testGetCartById() {
		Cart cart  = cartService.getCartById(3L);
		Assertions.assertThat(cart.getId()).isEqualTo(3L);
	}

	@Test
	void testGetAllByUserCart() {
		List<Cart> cartlist = cartService.getAllByUserCart(3L);
		Assertions.assertThat(cartlist.size()).isGreaterThan(0);

	}


}

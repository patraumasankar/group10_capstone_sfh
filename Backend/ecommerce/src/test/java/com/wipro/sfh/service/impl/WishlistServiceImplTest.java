package com.wipro.sfh.service.impl;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.sfh.entity.Wishlist;
import com.wipro.sfh.service.IWishlistService;

@SpringBootTest
class WishlistServiceImplTest {

	@Autowired
	private IWishlistService wishlistService;
	
	@Test
	void testGetAllByUserWishlist() {
		List<Wishlist> wishlist = wishlistService.getAllByUserWishlist(3L);
		Assertions.assertThat(wishlist.size()).isGreaterThan(0);
	}

}

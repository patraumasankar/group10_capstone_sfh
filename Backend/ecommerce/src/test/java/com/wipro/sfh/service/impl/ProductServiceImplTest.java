package com.wipro.sfh.service.impl;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.wipro.sfh.entity.Product;
import com.wipro.sfh.service.IProductService;

@SpringBootTest
class ProductServiceImplTest {

	@Autowired
	private IProductService productService;
	
	@Test
	void testGetAllProduct() {
		List<Product> userlist = productService.getAllProduct();
		Assertions.assertThat(userlist.size()).isGreaterThan(0);
	}

	@Test
	void testGetProductPage() {
		Page<Product> productlist = productService.getProductPage(0);
		Assertions.assertThat(productlist.getSize()).isGreaterThan(0);
	}

	@Test
	void testGetProductbyCategory() {
		List<Product> productlist = productService.getProductbyCategory("DECOR");
		Assertions.assertThat(productlist.size()).isGreaterThan(0);
	}

	@Test
	void testGetProductById() {
		Product product = productService.getProductById(1L);
		Assertions.assertThat(product.getId()).isEqualTo(1L);
	}

	@Test
	void testGetProductSortByInAsc() {
		Page<Product> productlist = productService.getProductSortByInAsc("productName", 0);
		Assertions.assertThat(productlist.getSize()).isGreaterThan(0);
	}

	@Test
	void testGetProductSortByInDesc() {
		Page<Product> productlist = productService.getProductSortByInDesc("productName", 0);
		Assertions.assertThat(productlist.getSize()).isGreaterThan(0);
	}

}

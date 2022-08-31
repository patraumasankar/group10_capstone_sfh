package com.wipro.sfh.service.impl;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.sfh.entity.MainOrder;
import com.wipro.sfh.service.IOrderService;

@SpringBootTest
class OrderServiceImplTest {

	@Autowired
	private IOrderService orderService;

	@Test
	void testGetAllOrder() {
		List<MainOrder> orders = orderService.getAllOrder();
		Assertions.assertThat(orders.size()).isGreaterThan(0);
	}

}

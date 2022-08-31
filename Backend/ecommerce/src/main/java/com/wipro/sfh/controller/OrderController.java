package com.wipro.sfh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sfh.dto.OrderDTO;
import com.wipro.sfh.entity.MainOrder;
import com.wipro.sfh.service.IOrderService;

/**
 * @author Umasankar
 * Modified Date: 28-08-2022
 * Description: order controller
 * 
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping("/getall")
	public List<MainOrder> getallOrder(){
		return orderService.getAllOrder();
	}
	
	@PostMapping("/place-order")
	public MainOrder placeOrder(@RequestBody OrderDTO dto) {
		
		return orderService.placeOrder(dto);
		
	}
	
}

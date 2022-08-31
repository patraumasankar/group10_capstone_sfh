package com.wipro.sfh.service;

import java.util.List;

import com.wipro.sfh.dto.OrderDTO;
import com.wipro.sfh.entity.MainOrder;

/**
 * @author Umasankar
 * Modified Date: 28-08-2022
 * Description: order service interface
 * 
 */

public interface IOrderService {

	public MainOrder placeOrder(OrderDTO order);
	
	public List<MainOrder> getAllOrder();
	
}

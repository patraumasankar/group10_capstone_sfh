package com.wipro.sfh.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.sfh.dto.OrderDTO;
import com.wipro.sfh.entity.Cart;
import com.wipro.sfh.entity.MainOrder;
import com.wipro.sfh.entity.Product;
import com.wipro.sfh.entity.User;
import com.wipro.sfh.repository.OrderRepository;
import com.wipro.sfh.service.ICartService;
import com.wipro.sfh.service.IOrderService;
import com.wipro.sfh.service.IProductService;
import com.wipro.sfh.service.IUserService;

/**
 * @author Umasankar
 * Modified Date: 28-08-2022
 * Description: order service implementation 
 * 
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICartService cartService;

	@Autowired
	private IProductService productService;

	/**
	 * @author Umasankar
	 * Modified Date: 28-08-2022
	 * Description: check out cart 
	 * Param: OrderDTO
	 * Return Type: MainOrder
	 */
	@Override
	public MainOrder placeOrder(OrderDTO order) {

		MainOrder newOrder = new MainOrder();

		User user = userService.getUserByUsername(order.getUsername());
		
		newOrder.setUser(user);
		newOrder.setUserEmail(user.getEmail());
		newOrder.setUserFirstname(user.getFirstName());
		newOrder.setUserLastname(user.getLastName());
		newOrder.setUserPhone(user.getPhone());
		newOrder.setTotalPrice(order.getTotalPrice());
		newOrder.setStatus("CONFIRMED");
		newOrder.setDate(LocalDate.now());
		List<Cart> cartlist = cartService.getAllByUserCart(user.getId());

		cartlist.stream().forEach((cart) -> {
			if (cart.getStatus() == 0) {
				Product product = productService.getProductById(cart.getProduct().getId());
				product.setProductStock(product.getProductStock() - cart.getQuantity());
			}
			cart.setStatus(1);
		});

		return orderRepository.save(newOrder);

	}

	/**
	 * @author Umasankar
	 * Modified Date: 28-08-2022
	 * Description: get order data
	 * Return Type: list of MainOrder
	 */
	@Override
	public List<MainOrder> getAllOrder() {
		return orderRepository.findAll();
	}

}

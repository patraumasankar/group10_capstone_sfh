package com.wipro.sfh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.sfh.dto.Email;
import com.wipro.sfh.entity.Cart;
import com.wipro.sfh.entity.Product;
import com.wipro.sfh.repository.CartRepository;
import com.wipro.sfh.service.ICartService;

/**
 * @author Partha
 * Modified Date: 28-08-2022
 * Description: cart service implementation 
 * 
 */

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private EmailService emailService;
	
	/**
	 * @author partha
	 * Modified Date: 27-08-2022
	 * Description: add to cart method
	 * Param: Cart
	 * Return Type: Cart
	 */
	
	@Override
	public Cart addToCart(Cart cart) {
			
		Product product = cart.getProduct();
		int stock = product.getProductStock();
		if(stock<10) {
			
			Email email = new Email();
			email.setRecipient("parthamaji560@gmail.com");
			email.setMsgBody("add more stock"+" for product id "+ product.getId());
			email.setSubject("update stock");
			
			emailService.sendSimpleEmail(email);
		}
		return cartRepository.save(cart);
	}

	/**
	 * @author partha
	 * Modified Date: 27-08-2022
	 * Description: get cart by cart id
	 * Param: id
	 * Return Type: Cart
	 */
	
	@Override
	public Cart getCartById(long id) {
		return cartRepository.findById(id).orElse(new Cart());
	}
	
	/**
	 * @author Partha
	 * Modified Date: 27-08-2022
	 * Description: get all cart by user id 
	 * Param: id
	 * Return Type: List of Cart
	 */
	
	@Override
	public List<Cart> getAllByUserCart(long id) {
		return cartRepository.findAllByUserId(id);
	}

	/**
	 * @author Partha
	 * Modified Date: 27-08-2022
	 * Description: delete cart
	 * Param: id
	 * Return Type: void
	 */
	@Override
	public void deleteCart(long id) {
		cartRepository.deleteById(id);
	}

	/**
	 * @author Umasankar
	 * Modified Date: 28-08-2022
	 * Description: update cart
	 * Param: Cart,id
	 * Return Type: Cart
	 */
	@Override
	public Cart updateCart(Cart cart, long id) {
		
		Cart updateCart = getCartById(id);
		
		updateCart.setUser(cart.getUser());
		updateCart.setProduct(cart.getProduct());
		updateCart.setQuantity(cart.getQuantity());
		updateCart.setTotalPrice(cart.getTotalPrice());
		
		return cartRepository.save(updateCart);
		
	}

	

}

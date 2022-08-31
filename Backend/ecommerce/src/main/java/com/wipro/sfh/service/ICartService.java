package com.wipro.sfh.service;

import java.util.List;

import com.wipro.sfh.entity.Cart;

/**
 * @author Partha
 * Modified Date: 27-08-2022
 * Description: cart service interface 
 * 
 */

public interface ICartService {

	public Cart addToCart(Cart cart);
	
	public Cart getCartById(long id);

	public List<Cart> getAllByUserCart(long id);
	
	public Cart updateCart(Cart cart,long id);

	public void deleteCart(long id);

}

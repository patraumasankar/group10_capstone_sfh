package com.wipro.sfh.controller;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sfh.entity.Cart;
import com.wipro.sfh.entity.Product;
import com.wipro.sfh.entity.User;
import com.wipro.sfh.service.ICartService;
import com.wipro.sfh.service.IProductService;
import com.wipro.sfh.service.IUserService;

/**
 * @author Akshat
 * Modified Date: 27-08-2022
 * Description: Cart controller 
 * 
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1/cart/")
public class CartController {

	@Autowired
	private ICartService service;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductService productService;

	@GetMapping("getall/{userId}")
	public List<Cart> getCart(@PathVariable long userId) {
		return service.getAllByUserCart(userId);
	}
	
	@GetMapping("/add/{productId}")
	public ResponseEntity<Cart> addCart(@PathVariable long productId, Principal principal) {

		Product product = productService.getProductById(productId);
		User user = userService.getUserByUsername(principal.getName());
		int quantity = 1;
		long totalPrice = quantity*product.getProductPrice();
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(quantity);
		cart.setTotalPrice(totalPrice);
		cart.setStatus(0);
		Cart addCart = service.addToCart(cart);
		return new ResponseEntity<Cart>(addCart, HttpStatus.CREATED);

	}
	
	@PutMapping("update/{cartId}")
	public ResponseEntity<Cart> updateUser(@RequestBody Cart cart,@PathVariable long cartId) {
		try {
			Cart updateCart = service.updateCart(cart, cartId);
			return new ResponseEntity<Cart>(updateCart, HttpStatus.OK);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{cartId}")
	public ResponseEntity<?> deletCart(@PathVariable long cartId) {

		service.deleteCart(cartId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

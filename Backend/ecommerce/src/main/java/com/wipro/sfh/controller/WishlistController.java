package com.wipro.sfh.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sfh.dto.WishlistDTO;
import com.wipro.sfh.entity.Product;
import com.wipro.sfh.entity.User;
import com.wipro.sfh.entity.Wishlist;
import com.wipro.sfh.service.IProductService;
import com.wipro.sfh.service.IUserService;
import com.wipro.sfh.service.IWishlistService;

/**
 * @author Umasankar
 * Modified Date: 27-08-2022
 * Description: wishlist controller 
 * 
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1/wishlist/")
public class WishlistController {

	@Autowired
	private IWishlistService service;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductService productService;

	@GetMapping("getall/{userId}")
	public List<Wishlist> getWishlist(@PathVariable long userId) {
		return service.getAllByUserWishlist(userId);
	}

	@GetMapping("/add/{productId}")
	public ResponseEntity<Wishlist> addWishList(@PathVariable long productId, Principal principal) {

		Product product = productService.getProductById(productId);
		User user = userService.getUserByUsername(principal.getName());
		WishlistDTO dto = new WishlistDTO();
		dto.setUser(user);
		dto.setProduct(product);
		Wishlist wishlist = service.addToWishlist(dto);
		return new ResponseEntity<Wishlist>(wishlist, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{wishlistId}")
	public ResponseEntity<?> deletWishList(@PathVariable long wishlistId) {

		service.deleteWishlist(wishlistId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

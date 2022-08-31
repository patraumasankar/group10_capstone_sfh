package com.wipro.sfh.service;

import java.util.List;

import com.wipro.sfh.dto.WishlistDTO;
import com.wipro.sfh.entity.Wishlist;

/**
 * @author Partha
 * Modified Date: 27-08-2022
 * Description: wishlist service interface
 * 
 */

public interface IWishlistService {
	
	public Wishlist addToWishlist(WishlistDTO wishlist);
	
	public List<Wishlist> getAllByUserWishlist(long id);
	
	public void deleteWishlist(long id);
	
}

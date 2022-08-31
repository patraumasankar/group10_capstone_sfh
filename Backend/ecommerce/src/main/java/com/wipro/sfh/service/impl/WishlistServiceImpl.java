package com.wipro.sfh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.sfh.dto.WishlistDTO;
import com.wipro.sfh.entity.Wishlist;
import com.wipro.sfh.repository.WishlistRepository;
import com.wipro.sfh.service.IWishlistService;

/**
 * @author Partha
 * Modified Date: 27-08-2022
 * Description: wishlist service implementation
 * 
 */

@Service
public class WishlistServiceImpl implements IWishlistService{

	@Autowired
	private WishlistRepository wishlistRepository;
	
	/**
	 * @author Partha
	 * Modified Date: 27-08-2022
	 * Description: add wishlist 
	 *  Param: WishlistDTO
	 * Return Type: Wishlist
	 */
	@Override
	public Wishlist addToWishlist(WishlistDTO wishlist) {
		
		Wishlist newWishlist = new Wishlist();
		
		newWishlist.setUser(wishlist.getUser());
		newWishlist.setProduct(wishlist.getProduct());
		
		return wishlistRepository.save(newWishlist);
	}

	/**
	 * @author Partha
	 * Modified Date: 27-08-2022
	 * Description: get user wishlist 
	 * Param: id
	 * Return Type: List of Wishlist
	 */
	@Override
	public List<Wishlist> getAllByUserWishlist(long id) {
		return wishlistRepository.findAllByUserId(id);
	}

	/**
	 * @author Partha
	 * Modified Date: 27-08-2022
	 * Description: delete wishlist 
	 *  Param: id
	 * Return Type: void
	 */
	@Override
	public void deleteWishlist(long id) {
		wishlistRepository.deleteById(id);
	}

}

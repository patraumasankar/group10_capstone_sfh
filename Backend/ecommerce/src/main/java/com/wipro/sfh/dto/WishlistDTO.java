package com.wipro.sfh.dto;

import com.wipro.sfh.entity.Product;
import com.wipro.sfh.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Akshat
 * Modified Date: 27-08-2022
 * Description: wishlist dto 
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDTO {
	
	private long id;
	
	private User user;
	
	private Product product;

}

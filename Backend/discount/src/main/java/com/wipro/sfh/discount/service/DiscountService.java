package com.wipro.sfh.discount.service;

import java.util.List;

import com.wipro.sfh.discount.dto.DiscountDTO;
import com.wipro.sfh.discount.entity.Discount;

/**
 * @author Partha
 * Modified Date: 25-08-2022
 * Description: discount service interface
 * 
 */
public interface DiscountService {
	
	List<Discount> createCoupon(DiscountDTO dto);

	void deleteCoupon(Long code);
	
	List<Discount> getAllCoupon();
	
	List<Discount> getAllCouponByUser(String username);
	
}

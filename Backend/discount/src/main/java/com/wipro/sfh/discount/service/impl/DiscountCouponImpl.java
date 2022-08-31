package com.wipro.sfh.discount.service.impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.sfh.discount.dto.DiscountDTO;
import com.wipro.sfh.discount.entity.Discount;
import com.wipro.sfh.discount.repository.DiscountRepo;
import com.wipro.sfh.discount.service.DiscountService;

/**
 * @author Partha
 * Modified Date: 28-08-2022
 * Description: discount service implementation 
 * 
 */

@Service
public class DiscountCouponImpl implements DiscountService {
	
	@Autowired
	private DiscountRepo repo;
	
	/**
	 * @author Umasankar
	 * Modified Date: 26-08-2022
	 * Description: create coupon for set of user 
	 * Param: DiscountDTO
	 * Return Type: List of Discount
	 */
	@Override
	public List<Discount> createCoupon(DiscountDTO dto) {
		
		List<Discount> discountList = new ArrayList<Discount>();
		for(String username: dto.getUsername()) {
		 Discount coupon=new Discount();
		 coupon.setCouponName(dto.getCouponName());
		 coupon.setValue(dto.getValue());
		 coupon.setUsername(username);
		 discountList.add(coupon);
		}
		return repo.saveAll(discountList);

	}
	/**
	 * @author Partha
	 * Modified Date: 26-08-2022
	 * Description: delete coupon 
	 *  Param: code
	 * Return Type:void
	 */
	@Override
	public void deleteCoupon(Long code) {
		 repo.deleteById(code);

	}
	/**
	 * @author Partha
	 * Modified Date: 26-08-2022
	 * Description: get all coupon
	 * Return Type: List of Discount
	 */
	@Override
	
	public List<Discount> getAllCoupon(){
		return repo.findAll();
	}
	/**
	 * @author Partha
	 * Modified Date: 28-08-2022
	 * Description: get coupon based on user 
	 *  Param: username
	 * Return Type: List of Discount
	 */
	@Override
	public List<Discount> getAllCouponByUser(String username) {
		return repo.findAllByUsername(username);
	}
	
}

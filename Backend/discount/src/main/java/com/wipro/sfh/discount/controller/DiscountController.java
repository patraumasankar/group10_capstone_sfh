package com.wipro.sfh.discount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sfh.discount.dto.DiscountDTO;
import com.wipro.sfh.discount.entity.Discount;
import com.wipro.sfh.discount.service.DiscountService;

/**
 * @author Partha
 * Modified Date: 25-08-2022
 * Description: dicount controller
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("api/v1/shop/coupon")
public class DiscountController {
	
	@Autowired
	private DiscountService service;
	
    @PostMapping("/add")
	public List<Discount> createCoupon(@RequestBody DiscountDTO dto) {
    	return service.createCoupon(dto);
    }
 
    @DeleteMapping("/delete/{code}")
	public ResponseEntity<Discount> deleteCoupon(@PathVariable("code") Long code) {
    	service.deleteCoupon(code);
    	return ResponseEntity.ok(null); 
    	
    }
    
    @GetMapping("/getall")
    public List<Discount> getAllCoupon(){
    	return service.getAllCoupon();
    }
    
    @GetMapping("/getall/{username}")
    public List<Discount> getAllCouponByUser(@PathVariable String username){
    	return service.getAllCouponByUser(username);
    }

}

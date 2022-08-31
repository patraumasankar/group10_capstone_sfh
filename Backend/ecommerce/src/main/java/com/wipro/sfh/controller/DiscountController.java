package com.wipro.sfh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wipro.sfh.dto.Discount;

/**
 * @author Umasankar
 * Modified Date: 29-08-2022
 * Description: Discount controller
 * 
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1/coupon")
public class DiscountController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getall")
    public List<Discount> getAllCoupon(){
    	
    	ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8282/api/v1/shop/coupon/getall", List.class);
    	
    	List<Discount> list = response.getBody();
    	
    	return list;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getall/{username}")
    public List<Discount> getAllCouponByUser(@PathVariable String username){
    	
    	ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8282/api/v1/shop/coupon/getall/"+username, List.class);
    	
    	List<Discount> list = response.getBody();
    	
    	return list;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/add")
    public List<Discount> saveCoupon(@RequestBody Discount coupon) {
		ResponseEntity<List> response = restTemplate.postForEntity("http://localhost:8282/api/v1/shop/coupon/add", coupon, List.class);
    	return response.getBody();
    }
    
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<?> deleteCoupon(@PathVariable long code)
    {
    	restTemplate.delete("http://localhost:8282/api/v1/shop/coupon/delete/"+code);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

}

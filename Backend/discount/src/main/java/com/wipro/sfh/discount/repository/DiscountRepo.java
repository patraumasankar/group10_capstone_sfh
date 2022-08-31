package com.wipro.sfh.discount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sfh.discount.entity.Discount;

/**
 * @author Partha
 * Modified Date: 25-08-2022
 * Description: discount repository
 * 
 */

@Repository
public interface DiscountRepo extends JpaRepository<Discount,Long>{

	List<Discount> findAllByUsername(String username);
	
}

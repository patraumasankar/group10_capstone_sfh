package com.wipro.sfh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sfh.entity.Cart;

/**
 * @author Akshat
 * Modified Date: 27-08-2022
 * Description: cart repository 
 * 
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	List<Cart> findAllByUserId(Long id);
}

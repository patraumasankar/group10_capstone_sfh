package com.wipro.sfh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sfh.entity.Product;

/**
 * @author Umasankar
 * Modified Date: 25-08-2022
 * Description: product repository 
 * 
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findAllByCategory(String category);
	
}

package com.wipro.sfh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sfh.entity.Wishlist;

/**
 * @author Jagadeeswar
 * Modified Date: 27-08-2022
 * Description: wishlist repository
 * 
 */

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

	List<Wishlist> findAllByUserId(Long id);
	
}

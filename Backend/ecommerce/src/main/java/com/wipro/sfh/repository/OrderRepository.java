package com.wipro.sfh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sfh.entity.MainOrder;

/**
 * @author Mani
 * Modified Date: 28-08-2022
 * Description: order repository interface
 * 
 */

@Repository
public interface OrderRepository extends JpaRepository<MainOrder, Long> {

}

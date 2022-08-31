package com.wipro.sfh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mani
 * Modified Date: 28-08-2022
 * Description: cart entity 
 * 
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_id")
	private long id;
	
	@ManyToOne()
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;
	
	private int quantity;
	
	private long totalPrice;
	
	private int status;
	
	
}

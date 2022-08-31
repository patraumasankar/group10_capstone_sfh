package com.wipro.sfh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Umasankar
 * Modified Date: 26-08-2022
 * Description: product entity 
 * 
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String productName;
	private long productPrice;
	private int productStock;
	private String productDescription;
	private String category;
	private String url;
	
}

package com.wipro.sfh.discount.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Partha
 * Modified Date: 26-08-2022
 * Description: discount entity
 * 
 */

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column
	private String couponName;
	
	@Column
	private String username;
	
	@Column(name="value")
	private Long value;
	
	
			
}

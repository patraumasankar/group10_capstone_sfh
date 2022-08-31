package com.wipro.sfh.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Partha
 * Modified Date: 28-08-2022
 * Description: Discount dto 
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
	
	private Long id;

	private String couponName;
	
	private List<String> username;
	
	private Long value;
}

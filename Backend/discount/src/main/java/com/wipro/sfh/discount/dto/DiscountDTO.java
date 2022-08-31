package com.wipro.sfh.discount.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Partha
 * Modified Date: 25-08-2022
 * Description: discount dto
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDTO {
	
	
	private Long id;

	private String couponName;
	
	private List<String> username;
	
	private Long value;
}

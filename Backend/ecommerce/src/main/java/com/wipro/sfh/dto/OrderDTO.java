package com.wipro.sfh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Umasankar
 * Modified Date: 28-08-2022
 * Description: order dto 
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	private String username;
	private long totalPrice;
	
}

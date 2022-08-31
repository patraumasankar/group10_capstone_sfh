package com.wipro.sfh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Umasankar
 * Modified Date: 26-08-2022
 * Description: product dto
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private long id;
	private String productName;
	private long productPrice;
	private int productStock;
	private String productDescription;
	private String category;
	private String url;
	
}

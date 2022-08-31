package com.wipro.sfh.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Umasankar
 * Modified Date: 25-08-2022
 * Description: jwt request
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
	
	private String username;
	private String password;
	
}

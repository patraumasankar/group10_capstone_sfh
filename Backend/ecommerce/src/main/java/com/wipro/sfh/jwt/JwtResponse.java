package com.wipro.sfh.jwt;

import com.wipro.sfh.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Umasankar
 * Modified Date: 25-08-2022
 * Description: jwt response class 
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	
	private User user;
	private String token;
}

package com.wipro.sfh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Umasankar
 * Modified Date: 26-08-2022
 * Description: user dto 
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private long phone;
	private String role;
}

package com.wipro.sfh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jagadeeswara
 * Modified Date: 28-08-2022
 * Description: Security Config class 
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

	private String recipient;
    private String msgBody;
    private String subject;
    
}

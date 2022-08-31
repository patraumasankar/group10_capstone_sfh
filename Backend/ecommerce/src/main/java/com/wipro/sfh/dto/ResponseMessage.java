package com.wipro.sfh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jagadeeswara
 * Modified Date: 28-08-2022
 * Description: message dto
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

	private String message;
	private String fileDownloadUri;
}

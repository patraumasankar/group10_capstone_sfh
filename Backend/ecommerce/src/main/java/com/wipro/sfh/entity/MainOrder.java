package com.wipro.sfh.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Umasankar
 * Modified Date: 29-08-2022
 * Description: order entity 
 * 
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MainOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne()
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	private String userFirstname;

	private String userLastname;

	private String userEmail;

	private long userPhone;

	private long totalPrice;

	private String status;
	
	private LocalDate date;

}
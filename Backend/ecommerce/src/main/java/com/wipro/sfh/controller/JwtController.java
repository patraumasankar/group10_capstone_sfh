package com.wipro.sfh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sfh.dto.UserDTO;
import com.wipro.sfh.entity.User;
import com.wipro.sfh.jwt.JwtRequest;
import com.wipro.sfh.jwt.JwtResponse;
import com.wipro.sfh.jwt.JwtUtil;
import com.wipro.sfh.service.impl.UserServiceImpl;

/**
 * @author Umasankar
 * Modified Date: 25-08-2022
 * Description: jwt controller 
 * 
 */

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}

		UserDetails userDetails = this.userService.loadUserByUsername(jwtRequest.getUsername());

		String token = this.jwtUtil.generateToken(userDetails);
		User user = this.userService.getUserByUsername(jwtRequest.getUsername());
		System.out.println("JWT " + token);
		return ResponseEntity.ok(new JwtResponse(user,token));

	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userService.addUser(user));
	}
	
}
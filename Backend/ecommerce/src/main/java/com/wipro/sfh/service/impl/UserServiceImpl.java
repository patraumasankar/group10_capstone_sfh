package com.wipro.sfh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.sfh.dto.UserDTO;
import com.wipro.sfh.entity.User;
import com.wipro.sfh.repository.UserRepository;
import com.wipro.sfh.service.IUserService;

/**
 * @author Umasankar
 * Modified Date: 26-08-2022
 * Description: user implementation class 
 * 
 */

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	/**
	 * @author Umasankar
	 * Modified Date: 26-08-2022
	 * Description: user details override method 
	 *  Param: username
	 * Return Type: UserDetails
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByUsername(username);		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
	}
	
	/**
	 * @author Umasankar
	 * Modified Date: 26-08-2022
	 * Description: add user to database
	 * Param: UserDTO
	 * Return Type: User
	 */
	
	@Override
	public User addUser(UserDTO user) {
		User newUser = new User();

		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setPhone(user.getPhone());
		newUser.setRole("ROLE_USER");

		return userRepository.save(newUser);
	}

	/**
	 * @author Umasankar
	 * Modified Date: 26-08-2022
	 * Description: get user data
	 * Return Type: List of User 
	 */
	
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	/**
	 * @author Umasankar
	 * Modified Date: 26-08-2022
	 * Description:get data by user id
	 * Param: id
	 * Return Type: User
	 */
	@Override
	public User getUserById(long uid) {
		return userRepository.findById(uid).orElse(new User());
	}

	/**
	 * @author Umasankar
	 * Modified Date: 26-08-2022
	 * Description: get data by user username
	 * Param: username
	 * Return Type: User
	 */
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	/**
	 * @author Umasankar
	 * Modified Date: 26-08-2022
	 * Description:update user
	 * Param: UserDTO
	 * Return Type: User
	 */
	@Override
	public User updateUser(UserDTO user) {

		User updateUser = getUserByUsername(user.getUsername());

		updateUser.setFirstName(user.getFirstName());
		updateUser.setLastName(user.getLastName());
		updateUser.setEmail(user.getEmail());
		updateUser.setUsername(user.getUsername());
		updateUser.setPhone(user.getPhone());
		updateUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		updateUser.setRole(updateUser.getRole());

		return userRepository.save(updateUser);
	}

	/**
	 * @author Umasankar
	 * Modified Date: 26-08-2022
	 * Description:delete user
	 * Param: id
	 * Return Type: void
	 */
	@Override
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	

}

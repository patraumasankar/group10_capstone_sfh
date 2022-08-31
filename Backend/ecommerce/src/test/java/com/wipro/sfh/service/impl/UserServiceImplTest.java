package com.wipro.sfh.service.impl;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.sfh.entity.User;
import com.wipro.sfh.service.IUserService;

@SpringBootTest
class UserServiceImplTest {
	
	@Autowired
	private IUserService userService;
	
	@Test
	void testGetAllUser() {
		List<User> userlist = userService.getAllUser();
		Assertions.assertThat(userlist.size()).isGreaterThan(0);
	}

	@Test
	void testGetUserById() {
		User user  = userService.getUserById(3L);
		Assertions.assertThat(user.getId()).isEqualTo(3L);
	}

	@Test
	void testGetUserByUsername() {
		User user  = userService.getUserByUsername("james123");
		Assertions.assertThat(user.getUsername()).isEqualTo("james123");
	}

}

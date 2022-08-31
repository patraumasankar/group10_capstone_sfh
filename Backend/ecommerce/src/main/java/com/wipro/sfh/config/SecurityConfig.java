package com.wipro.sfh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.wipro.sfh.jwt.JwtAuthenticationFilter;
import com.wipro.sfh.service.impl.UserServiceImpl;


/**
 * @author Umasankar
 * Modified Date: 28-08-2022
 * Description: Security Config class 
 * 
 */

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().authorizeRequests()

				.antMatchers("/api/v1/user/**", "/api/v1/product/add", "/api/v1/product/update/**",
						"/api/v1/product/delete/**","api/v1/coupon/add", "api/v1/coupon/delete/**","/api/csv/**")
				.hasRole(ADMIN)

				.antMatchers("/api/v1/product/get/**", "api/v1/wishlist/**","api/v1/cart/**" 
						,"api/v1/coupon/getall", "api/v1/order/**")
				.hasAnyRole(ADMIN, USER)

				.antMatchers("/", "/authenticate", "/register" , "/api/v1/product/getall/**").permitAll()

				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {

		return new RestTemplate();

	}

}

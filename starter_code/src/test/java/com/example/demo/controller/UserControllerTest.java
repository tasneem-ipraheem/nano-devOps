package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.UtilClass;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

public class UserControllerTest {
	
	private UserController userController ;
	private UserRepository userRepo = mock(UserRepository.class);
	private CartRepository cartRepo = mock(CartRepository.class);
	private BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);
	
	private User testUser ;
	ResponseEntity<User> responce ;
	
	@Before
	public void setUp(){
		userController = new UserController();

		UtilClass.injectObjects(userController, "userRepository", userRepo);
		UtilClass.injectObjects(userController, "cartRepository", cartRepo);
		UtilClass.injectObjects(userController, "bCryptPasswordEncoder", encoder);
		
		testUser = intializeUser();

	}
	
	
	@Test
	public void createUser()throws Exception{
		when(encoder.encode("pswrd123")).thenReturn("thisIsHashed");
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername("admin");
		request.setPassword("pswrd123");
		request.setConfirmPassword("pswrd123");

		responce = userController.createUser(request);
		assertNotNull(responce);
		assertEquals(200, responce.getStatusCodeValue());
		
		User user = responce.getBody();
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("thisIsHashed", user.getPassword());
		assertEquals(0, user.getId());

	}
	
	
	public User intializeUser(){
		when(encoder.encode("pswrd123")).thenReturn("thisIsHashed");
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername("admin");
		request.setPassword("pswrd123");
		request.setConfirmPassword("pswrd123");

		final ResponseEntity<User> responce = userController.createUser(request);
		
		return responce.getBody();
	}
	

}

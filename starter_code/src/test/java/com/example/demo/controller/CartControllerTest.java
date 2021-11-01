package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.UtilClass;
import com.example.demo.controllers.CartController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;

public class CartControllerTest {
	
	private CartController CartController ;
	private UserRepository userRepo = mock(UserRepository.class);
	private ItemRepository itemRepo = mock(ItemRepository.class);
	private CartRepository cartRepo = mock(CartRepository.class);

	private BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);
	

	
	private User testUser ;
	ResponseEntity<Cart> responce ;
	
	@Before
	public void setUp(){
		CartController = new CartController();

		UtilClass.injectObjects(CartController, "userRepository", userRepo);
		UtilClass.injectObjects(CartController, "cartRepository", cartRepo);
		UtilClass.injectObjects(CartController, "itemRepository", itemRepo);
		
		testUser = intializeUser();

	}
	
	
	@Test
	public void addTocart()throws Exception{
		testUser = intializeUser();
		ModifyCartRequest request = new ModifyCartRequest();
		request.setItemId(1);
		request.setQuantity(1);
		request.setUsername(testUser.getUsername());
		
		responce = CartController.addTocart(request);
		assertNotNull(responce);
		assertEquals(200, responce.getStatusCodeValue());
		
		Cart cart = responce.getBody();
		assertNotNull(cart);
		assertEquals("0", cart.getId());
		assertEquals(testUser, cart.getUser());

	}
	
	
	public User intializeUser(){
		User user = new User();
		user.setId(0);
		user.setUsername("admin");
		user.setPassword("pass12345");

		return user;
	}
	

}

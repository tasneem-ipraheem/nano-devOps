package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.example.demo.UtilClass;
import com.example.demo.controllers.CartController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;

public class CartControllerTest {
	
	private CartController CartController ;
	private UserRepository userRepo = mock(UserRepository.class);
	private ItemRepository itemRepo = mock(ItemRepository.class);
	private CartRepository cartRepo = mock(CartRepository.class);

	private User testUser ;
	private Item item ;
	ResponseEntity<Cart> responce;
	
	@Before
	public void setUp(){
		CartController = new CartController();

		UtilClass.injectObjects(CartController, "userRepository", userRepo);
		UtilClass.injectObjects(CartController, "cartRepository", cartRepo);
		UtilClass.injectObjects(CartController, "itemRepository", itemRepo);
		
		testUser = UtilClass.intializeUser();
		item = UtilClass.intializeItem();
		
		when(userRepo.findByUsername(testUser.getUsername())).thenReturn(testUser);
		when(userRepo.findById(0L)).thenReturn(Optional.of(testUser));
		when(itemRepo.findById(0L)).thenReturn(Optional.of(item));

	}
	
	
	@Test
	public void addTocart(){
		
		ModifyCartRequest request = new ModifyCartRequest();
		request.setItemId(0L);
		request.setQuantity(1);
		request.setUsername(testUser.getUsername());
		
		responce = CartController.addTocart(request);
		assertNotNull(responce);
		assertEquals(200, responce.getStatusCodeValue());
		
		Cart cart = responce.getBody();
		assertNotNull(cart);
		assertEquals("0", ""+cart.getId());
		assertEquals(testUser, cart.getUser());

	}
	
	

	

}

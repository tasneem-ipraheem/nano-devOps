package com.example.demo.controller;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.controllers.CartController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;

public class OrderControllerTest {
	
	private CartController CartController ;
	private UserRepository userRepo = mock(UserRepository.class);
	private ItemRepository itemRepo = mock(ItemRepository.class);
	private CartRepository cartRepo = mock(CartRepository.class);

	private User user ;
	
	private Item item1 ;
	private Item item2 ;
	private List<Item> items ;
	ResponseEntity<Cart> cartResponce;
	ResponseEntity<Item> itemResponce;
	ResponseEntity<List<Item>> listResponce;

	ResponseEntity<Cart> responce;
}

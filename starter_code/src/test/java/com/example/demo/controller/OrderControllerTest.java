package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.example.demo.UtilClass;
import com.example.demo.controllers.OrderController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;

public class OrderControllerTest {
	
	private OrderController orderController ;
	private UserRepository userRepo = mock(UserRepository.class);
	private OrderRepository orderRepo = mock(OrderRepository.class);

	private User user ;
	private UserOrder order ;
	private List<UserOrder> orders ;

	private Cart cart;
	private Item item1 ;
	private Item item2 ;
	private List<Item> items ;
	
	ResponseEntity<UserOrder> orderResponce;
	ResponseEntity<List<UserOrder>> orderListResponce;
	ResponseEntity<Cart> cartResponce;
	ResponseEntity<Item> itemResponce;
	ResponseEntity<List<Item>> listOfItemsResponce;

	@Before
	public void setUp(){
		orderController = new OrderController();

		UtilClass.injectObjects(orderController, "userRepository", userRepo);
		UtilClass.injectObjects(orderController, "orderRepository", orderRepo);

		user = UtilClass.intializeUser();

		item1 = UtilClass.intializeItem();
		item2 = UtilClass.intializeDifferentItem();
		
		items = new ArrayList<Item>();
		items.add(item1);
		items.add(item2);
		
		cart = user.getCart();
		cart.setItems(items);
		cart.setUser(user);
		
		order = new UserOrder();
		order.setUser(user);
		order.setId(0L);
		order.setItems(items);
		BigDecimal total = new BigDecimal( item1.getPrice().doubleValue() + item2.getPrice().doubleValue() );
		order.setTotal(total);
		
		orders = new ArrayList<UserOrder>();
		orders.add(order);
		
		when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
		when(orderRepo.findByUser(user)).thenReturn(orders);
		
	}
	
	@Test
	public void submit() {
		order = new UserOrder();
		order.setUser(user);
		order.setId(0L);
		order.setItems(items);
		BigDecimal total = new BigDecimal( item1.getPrice().doubleValue() + item2.getPrice().doubleValue() );
		order.setTotal(total);
		
		orderResponce = orderController.submit(user.getUsername());
		assertNotNull(orderResponce);
		assertEquals(200, orderResponce.getStatusCodeValue());
	}
	
	@Test
	public void getOrdersForUser(){
		orderListResponce = orderController.getOrdersForUser(user.getUsername());
		assertNotNull(orderListResponce);
		assertEquals(200, orderListResponce.getStatusCodeValue());
	}	
	
	
	
}

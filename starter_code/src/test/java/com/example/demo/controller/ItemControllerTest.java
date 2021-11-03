package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.example.demo.UtilClass;
import com.example.demo.controllers.ItemController;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;

public class ItemControllerTest {

	private ItemController itemController ;
	private ItemRepository itemRepo = mock(ItemRepository.class);

	private Item item1 ;
	private Item item2 ;
	private List<Item> items ;
	private List<Item> itemsByName;
	ResponseEntity<Item> responce;
	ResponseEntity<List<Item>> listResponce;
	
	

	
	@Before
	public void setUp(){
		itemController = new ItemController();

		UtilClass.injectObjects(itemController, "itemRepository", itemRepo);
		
		item1 = UtilClass.intializeItem();
		item2 = UtilClass.intializeDifferentItem();
		
		items = new ArrayList<Item>();
		items.add(item1);
		items.add(item2);
		
		
		itemsByName = new ArrayList<Item>();
		itemsByName.add(item1);
		
		when(itemRepo.findById(0L)).thenReturn(Optional.of(item1));
		when(itemRepo.findById(1L)).thenReturn(Optional.of(item2));
		
		when(itemRepo.findByName("Round Widget")).thenReturn(itemsByName);

		when(itemRepo.findAll()).thenReturn(items);

	}
	
	
	@Test
	public void getItems(){
		listResponce = itemController.getItems();
		assertNotNull(listResponce);
		assertEquals(200, listResponce.getStatusCodeValue());
		assertEquals(items.size(), listResponce.getBody().size());
		assertEquals(items.get(0), item1);

	}
	
	@Test
	public void getItemById(){
		responce = itemController.getItemById(0L);
		assertNotNull(responce);
		assertEquals(200, responce.getStatusCodeValue());
		assertEquals(item1, responce.getBody());
	}
	
	@Test
	public void getItemByName(){
		listResponce = itemController.getItemsByName("Round Widget");
		assertNotNull(listResponce);
		assertEquals(200, listResponce.getStatusCodeValue());
		assertEquals(item1, listResponce.getBody().get(0));
	}

}

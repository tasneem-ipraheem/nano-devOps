package com.example.demo;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;

public class UtilClass {
	
	public static void injectObjects(Object target, String fieldName, Object toInject) {
		boolean isPrivate = false;
		
		try {
			Field field = target.getClass().getDeclaredField(fieldName);
			
			if (!field.isAccessible()) {
				field.setAccessible(true);
				isPrivate = true;
			}
			field.set(target, toInject);
			
			if (isPrivate)
				field.setAccessible(false);
			
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static User intializeUser(){
		User user = new User();
		user.setId(0);
		user.setUsername("admin");
		user.setPassword("pass12345");
		Cart cart = new Cart();
		cart.setId(0L);
		cart.setUser(user);
		
		user.setCart(cart);

		return user;
	}
	
	public static Item intializeDifferentItem(){
		Item item = new Item();
		item.setId(1L);
		item.setName("Square Widget");
		item.setPrice(new BigDecimal(1.99));
		item.setDescription("A widget that is square");
		return item;
	}
	

	public static Item intializeItem(){
		Item item = new Item();
		
		item.setId(0L);
		item.setName("Round Widget");
		item.setPrice(new BigDecimal(2.99));
		item.setDescription("A widget that is round");
		return item;
	}

}

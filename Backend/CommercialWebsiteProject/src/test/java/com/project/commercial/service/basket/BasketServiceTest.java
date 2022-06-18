package com.project.commercial.service.basket;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doNothing;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.project.commercial.model.Basket;
import com.project.commercial.repository.BasketRepository;
import com.project.commercial.repository.OrderRepository;
import com.project.commercial.service.BasketService;



class BasketServiceTest {
	
	@InjectMocks
	private BasketService basketService;
	
	@Mock
	private BasketRepository basketRepository;
	
	@Mock
	private OrderRepository orderRepository;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}


	@Test
	void testFindOrderById() {
		
		Basket product = new Basket(1, "Americano", "Beef, mozarella, tomato", "M",5.0, 1);
		
		Mockito.when(basketRepository.findProductById(anyLong())).thenReturn(product);
		Basket newProduct = basketService.findOrderById(1);

		assertEquals(1, newProduct.getId());
	}

	@Test
	void testUpdateOrder() {
		
		Basket product = new Basket(1, "Americano", "Beef, mozarella, tomato", "M",5.0,1);
		
		Mockito.when(basketRepository.save(any(Basket.class))).thenReturn(product);
		Mockito.when(basketService.findOrderById(anyLong())).thenReturn(product);

		
		product = basketService.updateOrder(1, 2);
		assertNotNull(product.getId());
		assertEquals(2, product.getQuantity());
	}

	@Test
	void testDeleteProduct() {
		Basket product = new Basket(1, "Americano", "Beef, mozarella, tomato", "M",5.0, 1,1);
		
		doNothing().when(basketRepository).delete(product);
		
		Basket deleteProduct = basketService.deleteProduct(product);
		assertEquals(0, deleteProduct.getId());
		assertEquals(null, deleteProduct.getName());
		assertEquals(null, deleteProduct.getIngredients());
		assertEquals(null, deleteProduct.getSize());
		assertEquals(null, deleteProduct.getPrice());
		assertEquals(0, deleteProduct.getQuantity());
		assertEquals(0, deleteProduct.getUserId());

	}

	@Test
	void testCreateProduct() {
		
		Basket product = new Basket(1, "Americano", "Beef, mozarella, tomato", "M",5.0, 1,1);
		
		Mockito.when(basketRepository.save(any(Basket.class))).thenReturn(product);
		
		Basket product2 = basketService.createProduct(product);
		assertEquals(1, product2.getId());
		assertEquals("Americano", product2.getName());
		assertEquals("Beef, mozarella, tomato", product2.getIngredients());
		assertEquals("M", product2.getSize());
		assertEquals(5.0, product2.getPrice());
		assertEquals(1, product2.getQuantity());
		assertEquals(1, product2.getUserId());
	}

}

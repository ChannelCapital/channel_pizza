package com.project.commercial.repository.basket;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.project.commercial.model.Basket;
import com.project.commercial.repository.BasketRepository;
import com.project.commercial.service.BasketService;


class BasketRepositoryTest {
	
	@InjectMocks
	private BasketService basketService;
	
	@Mock
	private BasketRepository basketRepository;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindAll() {
		
		Basket product = new Basket(1, "Americano", "Beef, mozarella, tomato", "M",5.0, 1);
		Basket product2 = new Basket(2, "Italiano", "Mushroom, mozarella, tomato", "L",5.5, 1);
		List<Basket> aList = new ArrayList<>();
		aList.add(product);
		aList.add(product2);
		
		Mockito.when(basketRepository.findAll()).thenReturn(aList);
		assertEquals(2,aList.size());
	}

	@Test
	void testFindProductById() {
		Basket product = new Basket(1, "Americano", "Beef, mozarella, tomato", "M",5.0, 1);
		Basket product2 = new Basket(2, "Italiano", "Mushroom, mozarella, tomato", "L",5.5, 1);
		product2.setId(3);
		List<Basket> aList = new ArrayList<>();
		aList.add(product);
		aList.add(product2);
		
		Mockito.when(basketRepository.findProductById(anyLong())).thenReturn(product2);
	
		assertEquals(3, product2.getId());
	}



	@Test
	void testSaveBasket() {
		Basket product = new Basket("Italiano", "Mushroom, mozarella, tomato", "L",5.5, 1);
		Mockito.when(basketRepository.save(any(Basket.class))).thenReturn(product);
		
		assertNotNull(product.getId());
		assertEquals("Italiano", product.getName());
		assertEquals("Mushroom, mozarella, tomato", product.getIngredients());
		assertEquals("L", product.getSize());
		assertEquals(5.5, product.getPrice());
		assertEquals(1, product.getQuantity());

	}

	@Test
	void testDeleteBasket() {
		Basket product = new Basket("Italiano", "Mushroom, mozarella, tomato", "L",5.5, 1);
		basketService.deleteProduct(product);
		verify(basketRepository,times(1)).delete(product);
	}

}

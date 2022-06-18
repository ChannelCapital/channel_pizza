package com.project.commercial.repository.productDao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.project.commercial.model.Product;
import com.project.commercial.repository.ProductDao;

class ProductDaoTest {

	
	@Mock
	private ProductDao producDao;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test 
	void testFindAll(){
		
	Product product = new Product(1, "Greek", "Peproni, tomato, olive oil, cheese", "S/M/L","3£ / 4£ / 5£" );
	Product product2 = new Product(2, "Turkish", "Peproni, tomato, olive oil, cheese, sausage, pepperoni", "S/M/L","4£ / 5£ / 6£" );
	List<Product> aList = new ArrayList<>();
	aList.add(product);
	aList.add(product2);
	
	Mockito.when(producDao.findAll()).thenReturn(aList);
	assertEquals(2,aList.size());
	}
}

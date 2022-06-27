package edu.sabanciuniv.project;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PizzaBackEndApplicationTests {

	/*
	@Mock
	AddressRepository addressRepository;
	
	@Mock
	CartRepository cartRepository;
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	CartController cartController;
	
	@InjectMocks
	ProductController productController;
	
	@InjectMocks
	UserController userController;
	
	@Test
	void testGetProductById() {
		Product product= new Product();
		Product savedProduct= new Product();
		savedProduct.setId("123");
		when(productRepository.save(product)).thenReturn(savedProduct);
		ResponseEntity<Product> outputProduct = productController.createProduct(product);
		assertNotNull(outputProduct);
		assertNotNull(outputProduct.getBody().getId());
		assertEquals("123",outputProduct.getBody().getId());
		verify(productRepository).save(product);
	}
	*/

}

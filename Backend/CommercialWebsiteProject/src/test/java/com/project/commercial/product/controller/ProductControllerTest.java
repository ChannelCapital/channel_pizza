package com.project.commercial.product.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.project.commercial.controller.ProductController;
import com.project.commercial.controller.SequenceGeneratorService;
import com.project.commercial.repository.ProductDao;


@WebMvcTest(ProductController.class)
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductDao productDao;
	
	@MockBean
	private SequenceGeneratorService service;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

		@Test
		void testfindAllProducts() throws Exception {
			mockMvc.perform(get("/products")).
			andExpect(status().isOk());
		}
	

}

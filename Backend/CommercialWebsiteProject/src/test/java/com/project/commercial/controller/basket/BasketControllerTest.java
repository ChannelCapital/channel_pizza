package com.project.commercial.controller.basket;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.project.commercial.controller.BasketController;
import com.project.commercial.controller.SequenceGeneratorService;
import com.project.commercial.repository.BasketRepository;
import com.project.commercial.service.BasketService;


@WebMvcTest(BasketController.class)
class BasketControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BasketService basketService;
	
	@MockBean
	private BasketRepository basketRepository;
	
	@MockBean
	private SequenceGeneratorService service;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testfindAllOrders() throws Exception {
		mockMvc.perform(get("/basket")).
		andExpect(status().isOk());
	}
	
	@Test
	void testfindBasketById() throws Exception {
		mockMvc.perform(post("/orders/10")).
		andExpect(status().isOk());
	}
	
	@Test
	void testaddProduct() throws Exception {
		mockMvc.perform(post("/addProduct/2/Margaritta/{motzarella/L/4/1/10")).
		andExpect(status().isOk());
	}
	
	@Test
	void testupdateProduct() throws Exception {
		mockMvc.perform(put("/updateProduct/3/4")).
		andExpect(status().isOk());
	}
	
	
	@Test
	void testdeleteProduct() throws Exception {
		mockMvc.perform(post("/deleteProduct/3")).
		andExpect(status().isOk());
	}
	
	@Test
	void testdeleteBasket() throws Exception {
		mockMvc.perform(post("/deleteBasket")).
		andExpect(status().isOk());
	}
	
	

}

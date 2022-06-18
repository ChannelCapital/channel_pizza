package com.project.commercial.controller.order;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.project.commercial.controller.BasketController;
import com.project.commercial.controller.OrderController;
import com.project.commercial.controller.SequenceGeneratorService;
import com.project.commercial.repository.BasketRepository;
import com.project.commercial.repository.OrderRepository;
import com.project.commercial.service.OrderService;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService orderService;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private SequenceGeneratorService service;
	
	@MockBean
	private BasketRepository basketRepository;
	
	@MockBean
	private BasketController basketController;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testfindAllOrders() throws Exception {
		mockMvc.perform(get("/orders")).
		andExpect(status().isOk());
	}

	@Test
	void testcreateOrder() throws Exception {
		mockMvc.perform(post("/createOrder/21")).
		andExpect(status().isOk());
	}

}

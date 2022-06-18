package com.project.commercial.user.controller;

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

import com.project.commercial.controller.SequenceGeneratorService;
import com.project.commercial.controller.UserController;
import com.project.commercial.repository.UserRepository;
import com.project.commercial.service.UserService;



@WebMvcTest(UserController.class)
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private SequenceGeneratorService service;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindAllUsers() throws Exception {
		mockMvc.perform(get("/users")).
		andExpect(status().isOk());
	}

	@Test
	void testFindUserById() throws Exception {
		mockMvc.perform(get("/id/1")).
		andExpect(status().isOk());
	}
	
	@Test
	void testfindUserByEmail() throws Exception {
		mockMvc.perform(get("/email/abc@hotmail")).
		andExpect(status().isOk());
	}
	
	@Test
	void testcreateNewUser() throws Exception {
		mockMvc.perform(post("/add/abc@hotmail/123/555/England/Thompson/West/London/EJC")).
		andExpect(status().isOk());
	}
	
	
	@Test
	void testUpdateUser() throws Exception {
		mockMvc.perform(put("/update/1/tugce/678abc")).
		andExpect(status().isOk());
	}

	@Test
	void testDeleteUser() throws Exception {
		mockMvc.perform(post("/delete/100")).
		andExpect(status().isOk());
	}
	
	
}

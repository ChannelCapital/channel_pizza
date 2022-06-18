package com.project.commercial.controller.sequenceGenerator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//import static org.mockito.Mockito.doNothing;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.project.commercial.controller.SequenceGeneratorService;
import com.project.commercial.model.Order;
import com.project.commercial.model.User;
import com.project.commercial.repository.UserRepository;
import com.project.commercial.service.UserService;

class SequenceGeneratorTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	
	@Mock
	private SequenceGeneratorService service;
	

	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGenerateSequence() {
		
	User newUser = new User("dfg@gmail", "123", "123123", "Ashton", "Liverpool", "West", "UK", "A65");
	newUser.setId(service.generateSequence(User.SEQUENCE_NAME));
	
		Mockito.when(userRepository.save(any(User.class))).thenReturn(newUser);
		Mockito.when(service.generateSequence(anyString())).thenReturn(1);

		assertNotNull(newUser.getId());
		verify(service,times(1)).generateSequence("users_sequence");

		
	}

}

package com.project.commercial.repository.user;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.project.commercial.model.User;
import com.project.commercial.repository.UserRepository;
import com.project.commercial.service.UserService;


class UserRepositoryTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindAll() {
		User newUser = new User("dfg@gmail", "123", "123123", "Ashton", "Liverpool", "West", "UK", "A65");
		User newUser2 = new User("abc@gmail", "5676", "5554433", "Huston", "Barkley", "East", "UK", "A59");
		List<User> aList = new ArrayList<>();
		aList.add(newUser);
		aList.add(newUser2);
		
		Mockito.when(userRepository.findAll()).thenReturn(aList);
		assertEquals(2,aList.size());
		
	
	}

	@Test
	void testFindEmail() {
		User newUser = new User("abc@gmail", "5676", "5554433", "Huston", "Barkley", "East", "UK", "A59");
		User newUser2 = new User("def@gmail", "123", "5554433", "Huston", "Barkley", "East", "UK", "A59");
		newUser.setEmail("abcdef@hotmail");
		List<User> aList = new ArrayList<>();
		aList.add(newUser);
		aList.add(newUser2);
		
		Mockito.when(userRepository.findUserByEmail(anyString())).thenReturn(newUser);
	
		assertEquals("abcdef@hotmail", newUser.getEmail());
	}

	@Test
	void testFindUserById() {
		User newUser = new User("abc@gmail", "5676", "5554433", "Huston", "Barkley", "East", "UK", "A59");
		newUser.setId(1);
		
		Mockito.when(userRepository.findUserById(anyLong())).thenReturn(newUser);

		assertEquals(1, newUser.getId());
	}

	@Test
	void testSaveUser() {
		User newUser = new User("abc@gmail", "5676", "5554433", "Huston", "Barkley", "East", "UK", "A59");
		Mockito.when(userRepository.save(any(User.class))).thenReturn(newUser);
		
		assertNotNull(newUser.getId());
		assertEquals("abc@gmail", newUser.getEmail());
		assertEquals("5676", newUser.getPassword());
	}

	@Test
	void testDeleteUser() {
		User newUser = new User("dfg@gmail", "123", "123123", "Ashton", "Liverpool", "West", "UK", "A65");
		userService.deleteUser(newUser);
		verify(userRepository,times(1)).delete(newUser);
	}

}

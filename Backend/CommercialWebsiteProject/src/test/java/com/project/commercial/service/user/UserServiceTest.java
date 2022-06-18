package com.project.commercial.service.user;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doNothing;
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



class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	void testfindAll() {
		User newUser = new User("abc@gmail", "5676", "5554433", "Huston", "Barkley", "East", "UK", "A59");
		User newUser2 = new User("dfg@gmail", "123", "123123", "Ashton", "Liverpool", "West", "UK", "A65");
		List<User> aList = new ArrayList<>();
		aList.add(newUser);
		aList.add(newUser2);
		
		Mockito.when(userRepository.findAll()).thenReturn(aList);
		
		List<User> newList = userService.findAll();

		assertEquals(2, newList.size());
		assertEquals("abc@gmail", newList.get(0).getEmail());
		assertEquals("dfg@gmail", newList.get(1).getEmail());
		
		
	}

	@Test
	void testFindUserById() {
		User newUser = new User("abc@gmail", "5676", "5554433", "Huston", "Barkley", "East", "UK", "A59");;
		newUser.setId(1);
		
		Mockito.when(userRepository.findUserById(anyLong())).thenReturn(newUser);
		User newUser2 = userService.findUserById(1);

		assertEquals(1, newUser2.getId());
	}

	@Test
	void testCreateUser() {
		User newUser = new User("dfg@gmail", "123", "123123", "Ashton", "Liverpool", "West", "UK", "A65");
		
		Mockito.when(userRepository.save(any(User.class))).thenReturn(newUser);
		
		User newUser2 = userService.createUser(newUser);
		assertNotNull(newUser2.getId());
		assertEquals("dfg@gmail", newUser2.getEmail());
		assertEquals("123", newUser2.getPassword());
		
	}

	@Test
	void testFindAllByEmail() {
		User newUser = new User("tugce@gmail", "123434", "12312355", "Ben", "Oakley", "South", "UK", "B56");
		List<User> aList = new ArrayList<>();
		aList.add(newUser);
	
		Mockito.when(userRepository.findByEmail(anyString())).thenReturn(aList);
		List<User> newList = userService.findAllByEmail("tugce@gmail");

		assertEquals("tugce@gmail", newList.get(0).getEmail());

	}
	
	@Test
	void testUpdateUser() {
		User newUser = new User(1,"asli@gmail", "123434", "234", "Ben", "Oakley", "North", "UK", "B56");
		
		Mockito.when(userRepository.save(any(User.class))).thenReturn(newUser);
		Mockito.when(userRepository.findUserById(anyLong())).thenReturn(newUser);

		newUser = userService.updateUser(1, "asli19@hotmail", "678asd");
		assertNotNull(newUser.getId());
		assertEquals("asli19@hotmail", newUser.getEmail());
		assertEquals("678asd", newUser.getPassword());
		
	}
	
	// This can add if needed.
	/*
	@Test
	void testDeleteUser() {
		User newUser = new User("Fatih","1987abc");
		
		doNothing().when(userRepository).delete(newUser);
		
		User deleteUser = userService.deleteUser(newUser);
		assertEquals(0, deleteUser.getId());
		assertEquals(null, deleteUser.getUsername());
		assertEquals(null, deleteUser.getPassword());
	}*/

}

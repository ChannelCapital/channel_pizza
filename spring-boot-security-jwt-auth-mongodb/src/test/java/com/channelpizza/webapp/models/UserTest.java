package com.channelpizza.webapp.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user = new User(
      "dummyuser","dummy@email.com","Dummy", "Dumdum", "B7 5SA", "AlphaDummy"
    );

    @Test
    void getUsername() {
        assertEquals("dummyuser", user.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("dummy@email.com", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("AlphaDummy", user.getPassword());
    }

    @Test
    void getFirstName() {
        assertEquals("Dummy", user.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Dumdum", user.getLastName());
    }

    @Test
    void getPostCode() {
        assertEquals("B7 5SA", user.getPostCode());
    }
}
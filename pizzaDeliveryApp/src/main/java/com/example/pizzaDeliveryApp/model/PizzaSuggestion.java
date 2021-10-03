package com.example.pizzaDeliveryApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "suggestion")
public class PizzaSuggestion {

	@Id
	private String id;
	private String name;
	private String email;
	private String subject;
	
	public PizzaSuggestion() {
		super();
	}

	public PizzaSuggestion(String name, String email, String subject) {
		super();
		this.name = name;
		this.email = email;
		this.subject = subject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "PizzaSuggestion [id=" + id + ", name=" + name + ", email=" + email + ", subject=" + subject + "]";
	}
	
	
	
	
	
}

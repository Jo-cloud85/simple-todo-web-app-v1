package com.in28minutes.springboot.buildtodowebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

/* One of the magic of Spring Boot auto-configuration is once you include JPA and h2database in pom.xml 
 * file, Spring Boot will start pre-configuring it. One of the pre-configuring steps is if it sees any 
 * entities, it automatically start creating tables in H2 and map the bean to database table. And when 
 * using @Entity, you need to label the Primary Key with @Id */

@Entity
public class Todo {
	
	@Id
	@GeneratedValue
	private int id;
	
	// Customize column names for your database table if you do not want default
	// @Column(name="name")
	private String username;
	
	@Size(min=10, message="Enter at least 10 characters.")
	private String description;
	private LocalDate targetDate;
	private boolean done;
	
	
	// Constructor #1 - default constructor
	public Todo() {
		
	}
	
	// Constructor #2
	public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}
}

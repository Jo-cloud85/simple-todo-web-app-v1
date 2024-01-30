package com.in28minutes.springboot.buildtodowebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	// Initialize todos
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	// Static initializer
	static {
		todos.add(new Todo(++todosCount, "in28minutes", "Learn Cloud and AWS", 
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn DevOps", 
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn Full Stack Dev", 
				LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream()
				.filter(predicate)
				.toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}
	
	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream()
			 .filter(predicate)
			 .findFirst()
			 .get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}

/* In Java, Predicate<? super Todo> is a generic type that represents a predicate, which is a functional 
 * interface used for testing conditions. Let's break down the components of this expression:
 * 
 * Predicate: This is the functional interface that represents a predicate. In Java, a predicate is a 
 * functional interface from the java.util.function package that takes a single argument and returns a 
 * boolean.
 * 
 * <? super Todo>: This is a wildcard generic type. The ? represents an unknown type, and super Todo 
 * specifies that the unknown type must be a superclass of Todo or Todo itself. This wildcard is used to 
 * allow flexibility in the types that can be used with the predicate. (super is for lower bound, while 
 * extends is for upper bound)
 * 
 * predicate: This is a variable of type Predicate<? super Todo>. It is assigned a lambda expression that 
 * takes a Todo object as an argument 
 * todo -> todo.getUsername().equalsIgnoreCase(username)). The lambda expression defines the condition that 
 * the predicate will test. */

package com.in28minutes.springboot.buildtodowebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/* The 1st parameter is the bean that the repository is managing.
 * 
 * A repository allows us to perform actions on the entities. With this repository extending the 
 * JpaRepository, you will see the magic of Data JPA because the TodoControllerJpa (not TodoController 
 * anymore), just need to use this interface and this will take care of whatever we have been writing 
 * in TodoService automatically. */

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	/* You still have to write this method because search by username, which is a field/attribute of the 
	 * todo bean is not directly available. Once you write this Spring Data JPA will automatically provide
	 * a method to search by username. */
	public List<Todo> findByUsername(String username);
}

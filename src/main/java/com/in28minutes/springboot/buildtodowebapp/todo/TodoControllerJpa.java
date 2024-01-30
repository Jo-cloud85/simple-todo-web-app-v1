package com.in28minutes.springboot.buildtodowebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	/* Instead of TodoService where the static list is, now we use TodoRepository to take care of the
	 * business logic. */
	
	private TodoRepository todoRepository;
	
	/* Rmb, this is constructor injection to auto-wire TodoRepository into the controller */
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	
	@RequestMapping(value="list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername(model);	
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
	}
	
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}
	
	
	/* Instead of using RequestParams to bind the todo description, date etc., we bind to the Todo bean 
	 * instead and use @Valid to validate the Todo object. In your jsp, you need to make sure you include 
	 * the tag library (now at header.jspf) and use form:form instead of just form tag in your todo.jsp. 
	 * The Todo object becomes a form backing object, or a command bean. So whatever form in todo.jsp, we 
	 * are using todo bean as the backing object. */
	
	/* This binds the form, along w/ the data the user entered, back to the todo bean.
	 * With @Valid, the todo bean will get validated first before the binding happens. */
	
	/* This binds the todo bean to the form in todo.jsp. */
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		
		// Casting type of 'model' to String
		String username = getLoggedInUsername(model);
		
		todo.setUsername(username);
		todoRepository.save(todo);
		
		/* You have to redirect to the URL not the JSP, that's why it is list-todos, not listTodos */
		return "redirect:list-todos";
	}
	
	
	@RequestMapping(value="delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		/* Rmb, this is POST. So the form will throw back description and target date, and omit id and 
		 * done since we hide both. The todo bean still have another field called username. In order to 
		 * not throw an error, and we don't want to hide the username info, we have to set the username 
		 * on the todo bean. */
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUsername(ModelMap model) {
		// return (String)model.get("name");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}

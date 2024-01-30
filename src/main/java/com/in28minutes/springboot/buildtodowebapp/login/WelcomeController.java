package com.in28minutes.springboot.buildtodowebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/* When you want to pass anything from your controller to JSP/view, you need a ModelMap.
 * Once you put it into the model, the view can automatically pick it up. */

/* Rmb that whenever you put something into a model, by default, it is only available for the scope
 * of that request. If you want to retain values across certain requests, one of the options is to use
 * session. You use @SessionAttributes annotation to put values in a session. 
 * You also need to include it in all the Controllers where you want to make use of that value, and in
 * this case, it is "name". */

@Controller
@SessionAttributes("name")
public class WelcomeController {
	/* By stating the method, I am telling this method that it should only handle GET requests. */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		model.put("name", getLoggedinUsername());
		return "welcome"; // Return the name of the JSP which is welcome
	}
	
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}

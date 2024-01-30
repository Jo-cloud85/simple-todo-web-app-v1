package com.in28minutes.springboot.buildtodowebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/* Ensure that this controller file is created in a sub-package of the package where the application
 * class is present i.e. todowebapp, in this case. */

@Controller // Rmb, this is a more specific version of @Component to tell Spring that this is a bean.
public class SayHelloController {
	
	// Without JSP ----------------------------------------------------------------------------
	
	/* Here you are trying to print a string to the web page. W/o @ResponseBody annotation, you'd 
	 * be getting an error. What Spring MVC does is when you try to return a string back, it will
	 * look for a view with that specific name which it will not find, thus, throwing an error. It
	 * will not return this string back as is. This @ResponseBody annotation will return to the 
	 * browser whatever is returned by this message i.e. the string we have written here. */
	
	@RequestMapping("say-hello") // We are mapping a request URL to this method sayHello()
	@ResponseBody
	public String sayHello() {
		return "Hello! What are you learning today!";
	}
	
	// Too tedious when HTML gets more complex so we use JSP to help us create views instead
	//	@RequestMapping("say-hello-html")
	//	@ResponseBody
	//	public String sayHelloHTML() {
	//		StringBuffer sb = new StringBuffer();
	//		sb.append("<html>");
	//		sb.append("<head>");
	//		sb.append("<title> My First HTML page </title>");
	//		sb.append("</head>");
	//		sb.append("</html>");
	//		return sb.toString();
	//	}
	
	
	// With JSP -----------------------------------------------------------------------------
	/* Must rmb to add apache tomcat dependency in pom.xml, and add your prefix & suffix in
	 * application.properties */
	
	// No need for @ResponseBody as you have JSP
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello"; // Return the name of the JSP which is sayHello
	}
}

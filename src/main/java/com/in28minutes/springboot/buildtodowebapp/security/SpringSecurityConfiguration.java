package com.in28minutes.springboot.buildtodowebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	// LDAP or Database
	// In Memory
	
	// InMemoryUserDetailsManager is an interface so you cannot directly create an instance
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
		UserDetails userDetails2 = createNewUser("ranga", "dummydummy");
		
		// InMemoryUserDetailsManager() method accepts multiple arguments
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}
	
	
	private UserDetails createNewUser(String username, String password) {
		// Lambda function which accepts a String as input and returns String as output.
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER", "ADMIN")
									.build();
		return userDetails;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		
		// Deprecated already
		// http.csrf().disable();
		// http.headers().frameOptions().disable();
		
		http.formLogin(Customizer.withDefaults());
	    http.csrf(csrf -> csrf.disable());
	    http.headers(header -> header.frameOptions(frameOptions -> frameOptions.disable()));
		return http.build();
	}
}


/* W/ Spring Security, all URLs are protected. Unauthorized requests will default back to the
 * login form page.
 * 
 * The SecurityFilterChain is an interface which defines a filter chain which is capable of being matched 
 * against a HTTP servlet request. Whenever a web request comes in, it will be processed by this chain 
 * first. Earlier we saw that when we sent a request to list-todos if the user is not authenticated 
 * already, the login page is shown. That is done by this security filter chain. And we are now 
 * reconfiguring it. By default, it provides only these two features.
 * 
 * 1) Protect all URLs
 * 2) A login form is shown for unauthorized requests
 * 
 * Now we would want to add two more features to it, which is:
 * 3) Disable CSRF 
 * 4) Disable Frames
 * 
 * Note that when we override SecurityFilterChain, we need to define entire chain again.
 * 
 * You see that we also used Http security above. Http security is a class that allows configuring web 
 * based security for specific requests. By default it will be applied to all requests, but can be 
 * restricted using @RequestMatcher or other similar methods.
 * 
 * To use H2 console, you need to disable CSRF - Cross-Site Request Forgery AND we also need to disable 
 * frames as Spring Security does not allow both of them. 
 * 
 * X-Frame-Options enable => Frames CANNOT be used
 * h2-console uses frames => Disable X-Frame-Options header */

package com.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
     http.authorizeRequests()
     	.anyRequest()
     	.permitAll()
     	.and().csrf().disable();
     http.headers().frameOptions().disable();
	 }
	 
//	 @Override
//	 protected void configure(HttpSecurity http) throws Exception {
//		 http.authorizeRequests().antMatchers("/").permitAll().hasAnyRole("USER", "ADMIN")
//		 	.antMatchers("/books/all").hasAnyRole("USER", "ADMIN")
//	 		.andMatchers("/books/user/**").hasAnyRole("USER")
//	 		.antMatchers("/books/admin/**").hasAnyRole("ADMIN")
//	 		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
//		 	.and().logout().permitAll();
//
//		 http.csrf().disable();
//	 }
}
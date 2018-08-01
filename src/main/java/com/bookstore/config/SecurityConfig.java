package com.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest()
		.permitAll()
     	.and().csrf().disable();
		http.headers().frameOptions().disable();
//		http.authorizeRequests()
//				.antMatchers("/books/**").hasAnyRole("ADMIN","USER")
//				.anyRequest().permitAll()
//				.and()
//				.formLogin().loginPage("/").loginProcessingUrl("/login")
//				.usernameParameter("name").passwordParameter("password")
//				.defaultSuccessUrl("/books/all")
//				.and()
//				.logout().logoutUrl("/logout").logoutSuccessUrl("/")
//				.permitAll().and().csrf().disable();
	}
     
	/**@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
			 
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(getPasswordEncoder());
	}
	
	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder(){
			@Override
			public String encode(CharSequence rawPassword) {
				return null;
			}
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return true;
			}
		};
	}**/
}
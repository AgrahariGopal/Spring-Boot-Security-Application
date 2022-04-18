package com.learnspringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.learnspringsecurity.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		    .and()
		    .authorizeRequests()
		    .antMatchers("/signin").permitAll()
		    .antMatchers("/public/**").hasRole("EMPLOYEE")
		    .antMatchers("/users/**").hasRole("ADMIN")
		    .anyRequest()
		    .authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/signin");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//	auth.inMemoryAuthentication().withUser("john").password(this.passwordEncoder().encode("gopal123")).roles("EMPLOYEE");
	//	auth.inMemoryAuthentication().withUser("gopal").password(this.passwordEncoder().encode("gopal123")).roles("ADMIN");
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}

}

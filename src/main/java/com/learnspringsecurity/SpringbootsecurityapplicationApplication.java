package com.learnspringsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learnspringsecurity.models.User;
import com.learnspringsecurity.repo.UserRepository;

@SpringBootApplication
public class SpringbootsecurityapplicationApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootsecurityapplicationApplication.class, args);
	}
	
	public void run(String... args) throws Exception
	{
		User user = new User();
		user.setEmail("john@gmail.com");
		user.setUsername("john");
		user.setPassword(this.bCryptPasswordEncoder.encode("gopal"));
		user.setRole("ROLE_EMPLOYEE");
		
		this.userRepository.save(user);
		
		User user1 = new User();
		user1.setEmail("gopal@gmail.com");
		user1.setUsername("gopal");
		user1.setPassword(this.bCryptPasswordEncoder.encode("gopal"));
		user1.setRole("ROLE_ADMIN");
		
		this.userRepository.save(user1);
	}

}

package com.spring;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.model.Role;
import com.spring.model.User;
import com.spring.repo.RoleRepository;
import com.spring.repo.UserRepository;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Radhey Radhey...");
		
		User user=new User();
		user.setEmail("komal@gmail.com");
		user.setName("komal");
		user.setUsername("user");
		user.setPassword(passwordEncoder.encode("user"));
		Role roleName = new Role("ROLE_USER");
		
		Set<Role>roles =new HashSet<>();
		roles.add(roleName);
		user.setRoles(roles);
		
		//userRepository.save(user);
		
	}

}

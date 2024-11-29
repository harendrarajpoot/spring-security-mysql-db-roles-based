package com.spring.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Role;
import com.spring.model.User;
import com.spring.repo.RoleRepository;
import com.spring.repo.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@PostMapping("/signup")
	@Transactional
	public String signUp(@RequestBody User user) {
		System.out.println("-----------------Radhey radhey");

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<>();

		// for admin
		
		if (user.getRoles() != null) {
			System.out.println("ADMIN----------");
			Optional<Role> findRoleAdmin = roleRepository.findByName("ROLE_ADMIN");

			if (findRoleAdmin.isPresent()) {
				roles.add(findRoleAdmin.get());
			} else {
				Role role = new Role("ROLE_ADMIN"); 
				roles.add(role);
			}

		} else {

			System.out.println("ELSE------");
			// for user
			Optional<Role> findRole = roleRepository.findByName("ROLE_USER");

			if (findRole.isPresent()) {
				roles.add(findRole.get());
			}

			else { 
				Role role = new Role("ROLE_USER");
				roles.add(role);
			}
		}
		user.setRoles(roles);
		userRepository.save(user);
		return "SignUp";
	}

	@GetMapping("/login")
	public String loginPage() {
		System.out.println("-----------------Radhey radhey");
		return "login";
	}
}

package com.user_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_management.UserDTO;
import com.user_management.entity.User;
import com.user_management.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create")
	public ResponseEntity<String> createUser(@RequestBody @Validated UserDTO userDTO) {
		return userService.createUser(userDTO);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return userService.getAllUsers();
	}
}

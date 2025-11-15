package com.user_management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.user_management.UserDTO;
import com.user_management.entity.User;

public interface UserService {

	public ResponseEntity<String> createUser(UserDTO user);
	public ResponseEntity<List<User>> getAllUsers();

}

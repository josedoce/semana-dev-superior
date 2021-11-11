package com.sds.bootcamp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.bootcamp.app.dto.UserDTO;
import com.sds.bootcamp.app.dto.jwt.JwtRequest;
import com.sds.bootcamp.app.dto.jwt.JwtResponse;
import com.sds.bootcamp.app.service.UserService;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest body) throws Exception {
		JwtResponse token = userService
			.authenticate(new UserDTO(null, null, body.getEmail(), body.getPassword()));
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<JwtResponse> create(@RequestBody UserDTO body) throws Exception {
		JwtResponse token = userService.createUser(body);
		return ResponseEntity.ok(token);
	}
}

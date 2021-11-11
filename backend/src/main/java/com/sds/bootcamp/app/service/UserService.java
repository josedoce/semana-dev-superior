package com.sds.bootcamp.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sds.bootcamp.app.config.jwt.JwtTokenUtil;
import com.sds.bootcamp.app.dto.UserDTO;
import com.sds.bootcamp.app.dto.jwt.JwtResponse;
import com.sds.bootcamp.app.errors.exception.TesteException;
import com.sds.bootcamp.app.model.User;
import com.sds.bootcamp.app.repo.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		String email = username;
		if(userRepository==null) {
			//não sei se é possivel esse erro ocorrer :P
			throw new TesteException("Base is empty");
		}
		User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new TesteException("Email %s don't exists.".formatted(username));
		}
		
		String password = user.getPassword();
		return new org.springframework.security.core.userdetails.User(email, password, new ArrayList<>());			
	}
	
	public JwtResponse createUser(UserDTO user) throws Exception {
		
		User userExists = userRepository.findByUsername(user.getEmail());
		
		if(userExists!=null) {
			throw new Exception("User already exist.");
		}
		
		User userToSave = new User();
		userToSave.setFirstName(user.getFirstName());
		userToSave.setLastName(user.getLastName());
		userToSave.setEmail(user.getEmail());
		userToSave.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(userToSave);
		
		return authenticate(user);
	}
	
	public JwtResponse authenticate(UserDTO user) throws Exception {
		User userToToken = userRepository.findByUsername(user.getEmail());
		loadUserByUsername(user.getEmail());
		authenticate(user.getEmail(), user.getPassword());
		final String id = userToToken.getId().toString();
		final String email =userToToken.getEmail();
		final String token = jwtTokenUtil.generateToken(id, email);
		return new JwtResponse(token);
	}
	
	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		}catch(DisabledException e) {
			throw new Exception("User is disabled", e);		
		}catch(BadCredentialsException e) {
			throw new Exception("Password is invalid",e);
		}catch(TesteException e) {
			throw new Exception(e.getMessage());
		}
	}
	
}

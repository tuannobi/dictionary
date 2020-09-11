package com.tuan.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tuan.dictionary.user.User;
import com.tuan.dictionary.user.UserJwt;
import com.tuan.jwt.JwtUtil;

@RestController
public class JwtAuthenticationController {
	
	private AuthenticationManager authenticationManager;
	
	private JwtUtil jwtUtil;
	
	private UserDetailsService userDetailsService;
	
	@Autowired
	public JwtAuthenticationController(AuthenticationManager authenticationManager,JwtUtil jwtUtil, UserDetailsService userDetailsService) {
		this.authenticationManager=authenticationManager;
		this.jwtUtil=jwtUtil;
		this.userDetailsService=userDetailsService;
	}
	
	@PostMapping("/authenticate")
	public String createAuthenticationToken(@RequestBody UserJwt user) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect email or password!");
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(user.getEmail());
		final String jwt=jwtUtil.generateToken(userDetails);
//		return ResponseEntity.ok(new User());
		return jwt;
	}
}

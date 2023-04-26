package com.interpackage.users.auth.controller;

import com.interpackage.users.auth.model.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.interpackage.users.auth.model.AuthToken;
import com.interpackage.users.auth.model.UserCredential;
import com.interpackage.users.auth.service.LoginService;
import com.interpackage.users.redisConfig.JwtRedisCache;
import com.interpackage.users.redisConfig.repository.JwtRepository;

@RestController
@RequestMapping ("/api/users/v1")
public class LoginController {
	private final long expirationTime = 3600;
	@Autowired
	LoginService loginService;

	//@Autowired
	//JwtRepository jwtRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody UserCredential authenticationRequest)
			throws Exception {
		AuthToken token = loginService.doLogin(authenticationRequest.getUser(), authenticationRequest.getPassword());
		if (token != null) {

			//String key = authenticationRequest.getUser();
			//jwtRepository.save(key,new TokenCache(token.getAuthc(),expirationTime));
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@RequestMapping(value = "/verify-token", method = RequestMethod.GET)
	public ResponseEntity<?> verifyToken()
			throws Exception {
		String key = "HOLA";
		/*var exist = jwtRepository.exist(key);
		if(exist){
			jwtRepository.updateExpiration(key);
		}*/
		return ResponseEntity.status(HttpStatus.OK).build();
	}



}

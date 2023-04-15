package com.interpackage.users.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.interpackage.users.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username != null && !username.isBlank()) {
			try {
				var user = userService.getUserByName(username);
				return new User(username,user.getPassword(),new ArrayList<>());
			} catch (Exception e) {
			throw new UsernameNotFoundException("User not found with username: " + username);
			}
		} else {
			throw new UsernameNotFoundException("User not found - no username");
		}
	}

}
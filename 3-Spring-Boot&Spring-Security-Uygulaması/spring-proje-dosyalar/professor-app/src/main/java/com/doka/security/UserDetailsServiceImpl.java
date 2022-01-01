package com.doka.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doka.entity.Roles;
import com.doka.entity.User;
import com.doka.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.getUserByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		for(Roles role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		
		return new org.springframework.security.core.userdetails
		.User(user.getUsername(), user.getPassword(), authorities);
		
	}

}
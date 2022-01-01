package com.doka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doka.entity.User;
import com.doka.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
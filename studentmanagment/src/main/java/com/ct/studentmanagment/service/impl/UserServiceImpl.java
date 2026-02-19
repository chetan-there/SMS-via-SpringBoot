package com.ct.studentmanagment.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ct.studentmanagment.model.Users;
import com.ct.studentmanagment.repository.UserRepository;


@Service
public class UserServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Users users =userRepository.findByUsername(username)
	.orElseThrow( () -> new UsernameNotFoundException("Invalid Username") );
	
	return User.withUsername(username)
			.password(users.getPassword())
			.disabled(!users.isActive())
			.build();
	}

}

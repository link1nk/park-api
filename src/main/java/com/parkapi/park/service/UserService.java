package com.parkapi.park.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkapi.park.entities.User;
import com.parkapi.park.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuario não encontrado")
 		);
				
	}
}

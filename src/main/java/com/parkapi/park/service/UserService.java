package com.parkapi.park.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.parkapi.park.entities.User;
import com.parkapi.park.exception.UsernameUniqueViolationException;
import com.parkapi.park.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		try {
			return userRepository.save(user);
		} catch(DataIntegrityViolationException e) {
			throw new UsernameUniqueViolationException(String.format("Username {%s} ja cadastrado", user.getUsername()));
		}
	}

	@Transactional
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuario não encontrado")
 		);		
	}

	@Transactional
	public User updatePassword(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
		if (!novaSenha.equals(confirmaSenha)) {
			throw new RuntimeException("Nova senha nao confere com confirmação de senha");
		}
		
		User user = findById(id);
		
		if (!user.getPassword().equals(senhaAtual)) {
			throw new RuntimeException("Sua senha não confere");
		}
		
		user.setPassword(novaSenha);
		return user;
	}

	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}
}

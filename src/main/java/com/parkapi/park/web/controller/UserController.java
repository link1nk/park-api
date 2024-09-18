package com.parkapi.park.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkapi.park.entities.User;
import com.parkapi.park.service.UserService;
import com.parkapi.park.web.dto.UserPasswordDTO;
import com.parkapi.park.web.dto.UsuarioCreateDTO;
import com.parkapi.park.web.dto.UsuarioResponseDTO;
import com.parkapi.park.web.dto.mapper.UserMapper;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioCreateDTO userDTO) {
		User userSaved = userService.save(UserMapper.toUser(userDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(userSaved));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> create(@PathVariable Long id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(UserMapper.toDTO(user));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordDTO dto) {
		User userUpdated = userService.updatePassword(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> allUsers = userService.findAll();
		return ResponseEntity.ok().body(allUsers);
	}
}
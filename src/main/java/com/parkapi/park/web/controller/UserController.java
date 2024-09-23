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
import com.parkapi.park.web.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "Contem todas as operações relativas aos recursos para cadastro, edição e leitura de um usuario.")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Operation(summary = "Criar um novo usuario", description = "Recurso para criar um novo usuario",
			responses = {
					@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
					@ApiResponse(responseCode = "409", description = "Usuario e email ja cadastrado no sistema",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO userDTO) {
		User userSaved = userService.save(UserMapper.toUser(userDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(userSaved));
	}
	
	@Operation(summary = "Recuperar usuario pelo id", description = "Recuperar usuario pelo id",
			responses = {
					@ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> create(@PathVariable Long id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(UserMapper.toDTO(user));
	}
	
	@Operation(summary = "Atualizar senha", description = "Atualizar senha",
			responses = {
					@ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
					@ApiResponse(responseCode = "400", description = "Senha não confere",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Campos invalidos ou mal formatados",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
			}
	)
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDTO dto) {
		userService.updatePassword(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Listar todos os usuários", description = "Listar todos os usuários cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista com todos os usuários cadastrados",
                            content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDTO.class))))
            })
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
		List<User> allUsers = userService.findAll();
		return ResponseEntity.ok().body(UserMapper.toListDTO(allUsers));
	}
}
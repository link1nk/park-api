package com.parkapi.park.web.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.parkapi.park.entities.User;
import com.parkapi.park.web.dto.UsuarioCreateDTO;
import com.parkapi.park.web.dto.UsuarioResponseDTO;

public class UserMapper {

	public static User toUser(UsuarioCreateDTO createDTO) {
		return new ModelMapper().map(createDTO, User.class);
	}
	
	public static UsuarioResponseDTO toDTO(User user) {
		String role = user.getRole().name().substring("ROLE_".length());
		PropertyMap<User, UsuarioResponseDTO> props = new PropertyMap<User, UsuarioResponseDTO>() {
			@Override
			protected void configure() {
				map().setRole(role);
			}
		}; 
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(props);
		return  mapper.map(user, UsuarioResponseDTO.class);
	}
}

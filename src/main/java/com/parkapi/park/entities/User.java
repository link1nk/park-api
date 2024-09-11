package com.parkapi.park.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum Role {
		ROLE_ADMIN, ROLE_CLIENT
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username", nullable=false, unique=true, length=100)
	private String username;
	
	@Column(name="password", nullable=false, length=200)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", nullable=false, length=25)
	private Role role;
	
	@Column(name="dateCreate")
	private LocalDateTime dateCreate;

	@Column(name="dateUpdate")
	private LocalDateTime dateUpdate;
	
	@Column(name="createdBy")
	private String createdBy;
	
	@Column(name="modifiedBy")
	private String modifiedBy;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}
}

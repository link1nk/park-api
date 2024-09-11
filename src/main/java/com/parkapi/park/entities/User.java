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
	
	public User() {
	}
	
	public User(Long id, String username, String password, Role role, LocalDateTime dateCreate,
			LocalDateTime dateUpdate, String createdBy, String modifiedBy) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.dateCreate = dateCreate;
		this.dateUpdate = dateUpdate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public LocalDateTime getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(LocalDateTime dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

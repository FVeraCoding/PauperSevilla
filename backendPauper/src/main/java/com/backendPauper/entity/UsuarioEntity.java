package com.backendPauper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Usuario")
public class UsuarioEntity {
	

	public UsuarioEntity(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	

	public UsuarioEntity() {
	}


	public UsuarioEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@OneToOne(mappedBy = "usuario")
	private JugadorEntity jugador;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username", unique = true, length = 20, nullable = false)
	private String username;
	
	@Column(name="password", nullable = false)
	private String password;

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
	
	
	
	
}

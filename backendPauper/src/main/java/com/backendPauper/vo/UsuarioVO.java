package com.backendPauper.vo;

public class UsuarioVO {

	Long id;
	String username;
	String password;
	
	
	
	public UsuarioVO() {
	}

	public UsuarioVO(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public UsuarioVO(String username, String password) {
		this.username = username;
		this.password = password;
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
	
	
	
}

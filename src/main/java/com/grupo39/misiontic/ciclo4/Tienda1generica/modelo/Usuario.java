package com.grupo39.misiontic.ciclo4.Tienda1generica.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="usuarios")
public class Usuario {
	
	@Id
	private String id;
	private String username;
	private String password;
	private String nombre;
	private String email;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario( String username, String password, String nombre, String email) {
		super();
	
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}

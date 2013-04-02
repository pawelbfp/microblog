package com.microblog.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Users")
public class User  implements Serializable{

	private static final long serialVersionUID = 1L;
	String username;
	String password;
	Integer idUser;

	public User() {		
	}

	
	public User(Integer idUser, String username,String password) {
		super();		
		this.idUser = idUser;
		this.username = username;
		this.password = password;
	}

	
	@Id
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Column(nullable=false, length=64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
	
	@Column(nullable=false, length=64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

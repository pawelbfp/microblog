package com.microblog.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Posts")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	Integer idPost;
	Integer idUser;
	String userName;
	String body;
	Date date;

	public Post() {
	}

	public Post(Integer idUser, String body, Date date) {
		super();
		this.idUser = idUser;
		this.body = body;
		
		if (date == null)
			date = new Date(System.currentTimeMillis());
		this.date = date;
	}


	@Id
	@GeneratedValue
	public Integer getIdPost() {
		return idPost;
	}

	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}

	@Column(nullable = false)
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(nullable = false, length = 140)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(nullable = false)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

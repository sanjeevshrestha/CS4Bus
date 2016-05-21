package com.cs4.appointmentManagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sanjeev
 *
 */
@Entity
@Table(name="AUTHORITY")
public class Authority implements Serializable{

	/**
	 * 
	 */

	private static final long serialVersionUID = -5715736139883867873L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="AUTHORITY_ID")
	private long id;

	/**
	 * 
	 */
	@Column(name="username")
	private String username;
	
	/**
	 * 
	 */
	@Column(name="AUTHORITY", nullable = false)
	private String authority;
	
	/**
	 * 
	 */
	public Authority() {}

	/**
	 * @param username
	 * @param authority
	 */
	public Authority(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}



	/**
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}

package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="current_session")
public class CurrentSession {
	
	@Id
	@Column(name="ip_session")
	private String ip_session;
	
	public CurrentSession(){
		
	}

	public String getIp_session() {
		return ip_session;
	}

	public void setIp_session(String ip_session) {
		this.ip_session = ip_session;
	}
	
	

}

package com.bd.chia.jpa;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class AutoId {
	@Id
	protected String id;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AutoId() {
		id = UUID.randomUUID().toString();
	}	
}

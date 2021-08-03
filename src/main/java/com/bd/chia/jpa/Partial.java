package com.bd.chia.jpa;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Partial extends AutoId {
	@Indexed
	private String launcherId;
	private Long timestamp;
    private Integer difficulty;
    
	public String getLauncherId() {
		return launcherId;
	}
	public void setLauncherId(String launcherId) {
		this.launcherId = launcherId;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
}

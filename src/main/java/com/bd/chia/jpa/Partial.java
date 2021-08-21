package com.bd.chia.jpa;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@CompoundIndexes({
	@CompoundIndex(def = "{'launcherId':1, 'timestamp':-1}", name = "launcherId_timestamp")
})
public class Partial extends AutoId {
	@Indexed
	@Field(value = "launcher_id")
	private String launcherId;
	
	@Indexed
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

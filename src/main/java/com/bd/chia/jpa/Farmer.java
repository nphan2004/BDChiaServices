package com.bd.chia.jpa;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "farmer")
public class Farmer {
	@DynamoDBHashKey
	@DynamoDBAttribute(attributeName = "launcher_id")
	private String launcherId;
	
	private Long delay_time;
	
	private Boolean is_pool_member;
	
	private Integer points;
	
	private Integer difficulty;

	private String p2_singleton_puzzle_hash;
	
	private String payout_instructions;
	
	public String getLauncherId() {
		return launcherId;
	}

	public void setLauncherId(String launcherId) {
		this.launcherId = launcherId;
	}

	public Long getDelay_time() {
		return delay_time;
	}

	public void setDelay_time(Long delay_time) {
		this.delay_time = delay_time;
	}

	public Boolean getIs_pool_member() {
		return is_pool_member;
	}

	public void setIs_pool_member(Boolean is_pool_member) {
		this.is_pool_member = is_pool_member;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public String getP2_singleton_puzzle_hash() {
		return p2_singleton_puzzle_hash;
	}

	public void setP2_singleton_puzzle_hash(String p2_singleton_puzzle_hash) {
		this.p2_singleton_puzzle_hash = p2_singleton_puzzle_hash;
	}

	public String getPayout_instructions() {
		return payout_instructions;
	}

	public void setPayout_instructions(String payout_instructions) {
		this.payout_instructions = payout_instructions;
	}
}

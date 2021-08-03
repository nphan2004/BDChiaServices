package com.bd.chia.jpa;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Farmer {
	@Id
	private String launcherId;
	
	private Long delay_time;
	
	private Boolean is_pool_member;
	
	private Integer points;
	
	private Integer difficulty;

	private String p2_singleton_puzzle_hash;
	
	@Indexed
	@JsonProperty(value = "payout_instructions")
	private String payoutInstructions;
	
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

	public String getPayoutInstructions() {
		return payoutInstructions;
	}

	public void setPayoutInstructions(String payoutInstructions) {
		this.payoutInstructions = payoutInstructions;
	}
}

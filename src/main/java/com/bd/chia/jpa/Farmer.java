package com.bd.chia.jpa;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Farmer {
	@Id
	private String id;
	
	@Field(value = "launcher_id")
	@Indexed
	private String launcherId;
	
	@Field(value = "delay_time")
	private Long delayTime;
	
	@Indexed
	@Field(value = "is_pool_member")
	private Integer poolMember;
	
	private Integer points;
	
	private Integer difficulty;

	@Field(value = "p2_singleton_puzzle_hash")
	private String p2SingletonPuzzleHash;
	
	@Indexed
	@Field(value = "payout_instructions")
	private String payoutInstructions;
	
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLauncherId() {
		return launcherId;
	}

	public void setLauncherId(String launcherId) {
		this.launcherId = launcherId;
	}

	public Long getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Long delayTime) {
		this.delayTime = delayTime;
	}

	public Integer getPoolMember() {
		return poolMember;
	}

	public void setPoolMember(Integer poolMember) {
		this.poolMember = poolMember;
	}

	public String getP2SingletonPuzzleHash() {
		return p2SingletonPuzzleHash;
	}

	public void setP2SingletonPuzzleHash(String p2SingletonPuzzleHash) {
		this.p2SingletonPuzzleHash = p2SingletonPuzzleHash;
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

	public String getPayoutInstructions() {
		return payoutInstructions;
	}

	public void setPayoutInstructions(String payoutInstructions) {
		this.payoutInstructions = payoutInstructions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package com.bd.chia.jpa;

import java.util.Date;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndexes({
	@CompoundIndex(def = "{'launcherId':1, 'confirmedAtHeight':-1}", name = "launcherId_confirmedAtHeight")
})
public class Payout extends AutoId {
	@Indexed
	private String launcherId;
	
	private Long confirmedAtHeight;
	
	private Date payDate;
	
	private Long amount;
	
	private String puzzleHash;
	
	private String parentCoin;

	public String getLauncherId() {
		return launcherId;
	}

	public void setLauncherId(String launcherId) {
		this.launcherId = launcherId;
	}

	public Long getConfirmedAtHeight() {
		return confirmedAtHeight;
	}

	public void setConfirmedAtHeight(Long confirmedAtHeight) {
		this.confirmedAtHeight = confirmedAtHeight;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getPuzzleHash() {
		return puzzleHash;
	}

	public void setPuzzleHash(String puzzleHash) {
		this.puzzleHash = puzzleHash;
	}

	public String getParentCoin() {
		return parentCoin;
	}

	public void setParentCoin(String parentCoin) {
		this.parentCoin = parentCoin;
	}
}

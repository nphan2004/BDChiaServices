package com.bd.chia.jpa;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WinningRecord {
	public static final Integer INCOMING = 0;
	
	private Long amount;
	private Boolean confirmed;
	
	@Id
	private Long confirmedAtHeight;
	
	private Date createdAt;
	
	private Long feeAmount;
	
	private String name;
	
	private String toAddress;
	
	private Integer type;
	
	private Integer walletId;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Long getConfirmedAtHeight() {
		return confirmedAtHeight;
	}

	public void setConfirmedAtHeight(Long confirmedAtHeight) {
		this.confirmedAtHeight = confirmedAtHeight;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(Long feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
}

package com.bd.chia.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class WalletTransactions {
	public static class AnAddition {
		private Long amount;
		
		@SerializedName(value = "parent_coin_info")
		private String parentCoinInfo;
		
		@SerializedName(value = "puzzle_hash")
		private String puzzleHash;
		public Long getAmount() {
			return amount;
		}
		public void setAmount(Long amount) {
			this.amount = amount;
		}
		public String getParentCoinInfo() {
			return parentCoinInfo;
		}
		public void setParentCoinInfo(String parentCoinInfo) {
			this.parentCoinInfo = parentCoinInfo;
		}
		public String getPuzzleHash() {
			return puzzleHash;
		}
		public void setPuzzleHash(String puzzleHash) {
			this.puzzleHash = puzzleHash;
		}
		@Override
		public String toString() {
			return "AnAddition [amount=" + amount + ", parentCoinInfo=" + parentCoinInfo + ", puzzleHash=" + puzzleHash
					+ "]";
		}
	}
	
    private static class Adapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<T> defaultAdapter;

        Adapter(TypeAdapter<T> defaultAdapter) {
            this.defaultAdapter = defaultAdapter;
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
        }

        @Override
        public T read(JsonReader in) throws IOException {
            Long l = (Long) defaultAdapter.read(in);
            
            return (T) new Date(l*1000);
        }
    }
    
	public static class LongToDateConverter implements TypeAdapterFactory {
		@Override
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {			
			TypeAdapter defaultAdapter = gson.getAdapter(Long.class);
			return new Adapter(defaultAdapter);
		}		
	}
	
	public static class AWalletTransaction {
		private Long amount;
		private Boolean confirmed;
		
		@SerializedName("confirmed_at_height")
		private Long confirmedAtHeight;
		
		@SerializedName("created_at_time")
		@JsonAdapter(LongToDateConverter.class)
		private Date createdAt;
		
		@SerializedName("fee_amount")
		private Long feeAmount;
		
		private String name;
		
		@SerializedName("to_address")
		private String toAddress;
		
		private Integer type;
		
		@SerializedName("wallet_id")
		private Integer walletId;

		private List<AnAddition> additions;
		
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

		public List<AnAddition> getAdditions() {
			return additions;
		}

		public void setAdditions(List<AnAddition> additions) {
			this.additions = additions;
		}

		@Override
		public String toString() {
			return "AWalletTransaction [amount=" + amount + ", confirmed=" + confirmed + ", confirmedAtHeight="
					+ confirmedAtHeight + ", createdAt=" + createdAt + ", feeAmount=" + feeAmount + ", name=" + name
					+ ", toAddress=" + toAddress + ", type=" + type + ", walletId=" + walletId + ", additions="
					+ additions + "]";
		}
	}
	
	private Boolean success;
	private List<AWalletTransaction> transactions;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public List<AWalletTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<AWalletTransaction> transactions) {
		this.transactions = transactions;
	}
	public void addTransaction(AWalletTransaction tx) {
		if(transactions==null) {
			transactions = new ArrayList<AWalletTransaction>();
		}
		
		transactions.add(tx);
	}
	@Override
	public String toString() {
		return "WalletTransactions [success=" + success + ", transactions=" + transactions + "]";
	}
}

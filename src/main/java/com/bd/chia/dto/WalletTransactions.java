package com.bd.chia.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WalletTransactions {
	
//    private static class Adapter<T> extends TypeAdapter<T> {
//        private final TypeAdapter<T> defaultAdapter;
//
//        Adapter(TypeAdapter<T> defaultAdapter) {
//            this.defaultAdapter = defaultAdapter;
//        }
//
//        @Override
//        public void write(JsonWriter out, T value) throws IOException {
//        }
//
//        @Override
//        public T read(JsonReader in) throws IOException {
//            Long l = (Long) defaultAdapter.read(in);
//            
//            return (T) new Date(l*1000);
//        }
//    }
//    
//	public static class LongToDateConverter implements TypeAdapterFactory {
//		@Override
//		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {			
//			TypeAdapter defaultAdapter = gson.getAdapter(Long.class);
//			return new Adapter(defaultAdapter);
//		}		
//	}
	
	public static class AWalletTransaction {
		private Long amount;
		private Boolean confirmed;
		
		@SerializedName("confirmed_at_height")
		private Long confirmedAtHeight;
		
		@SerializedName("created_at_time")
		//@JsonAdapter(LongToDateConverter.class)
		private Long createdAt;
		
		@SerializedName("fee_amount")
		private Long feeAmount;
		
		private String name;
		
		@SerializedName("to_address")
		private String toAddress;
		
		private Integer type;
		
		@SerializedName("wallet_id")
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

		public Long getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Long createdAt) {
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

		@Override
		public String toString() {
			return "AWalletTransaction [amount=" + amount + ", confirmed=" + confirmed + ", confirmedAtHeight="
					+ confirmedAtHeight + ", createdAt=" + createdAt + ", feeAmount=" + feeAmount + ", name=" + name
					+ ", toAddress=" + toAddress + ", type=" + type + ", walletId=" + walletId + "]";
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

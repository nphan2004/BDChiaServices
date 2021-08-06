package com.bd.chia.dto;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

public class NodeInfo {
	public class BlockchainState {
		private Integer difficulty;
		private BigDecimal space;
		public Integer getDifficulty() {
			return difficulty;
		}
		public void setDifficulty(Integer difficulty) {
			this.difficulty = difficulty;
		}
		public BigDecimal getSpace() {
			return space;
		}
		public void setSpace(BigDecimal space) {
			this.space = space;
		}
		@Override
		public String toString() {
			return "BlockchainState [difficulty=" + difficulty + ", space=" + space + "]";
		}
	}
	
	@SerializedName(value = "blockchain_state")
	private BlockchainState blockChainstate;
	private Boolean success;
	
	@Override
	public String toString() {
		return "NodeInfo [blockChainstate=" + blockChainstate + ", success=" + success + "]";
	}
	public BlockchainState getBlockChainstate() {
		return blockChainstate;
	}
	public void setBlockChainstate(BlockchainState blockChainstate) {
		this.blockChainstate = blockChainstate;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}

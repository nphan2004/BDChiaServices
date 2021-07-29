package com.bd.chia.services;

import com.bd.chia.dto.WalletTransactions;

public interface IWalletTrackingRPC {
	WalletTransactions getWalletTransactions() throws Exception;
}

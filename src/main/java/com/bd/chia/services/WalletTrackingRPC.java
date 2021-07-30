package com.bd.chia.services;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.bd.chia.dto.WalletTransactions;
import com.google.gson.Gson;

@Component
@Profile({"aws"})
public class WalletTrackingRPC implements IWalletTrackingRPC {

	@Override
	public WalletTransactions getWalletTransactions() throws Exception {
		List<String> args = new ArrayList<String>();
		args.add("/usr/bin/curl");
		args.add("--insecure");
		args.add("--cert");
		args.add("/home/ubuntu/.chia/mainnet/config/ssl/wallet/private_wallet.crt");
		args.add("--key");
		args.add("/home/ubuntu/.chia/mainnet/config/ssl/wallet/private_wallet.key");
		args.add("-d");
		args.add("{'wallet_id': 1}");
		args.add("-H");
		args.add("Content-Type: application/json");
		args.add("-X");
		args.add("POST");
		args.add("https://172.31.12.180:9256/get_transactions");				

		ProcessBuilder pb = new ProcessBuilder(args);

		Gson gson = new Gson();
		WalletTransactions wt = gson.fromJson(new InputStreamReader(pb.start().getInputStream()), WalletTransactions.class);
		return wt;
	}
}

package com.bd.chia.services;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.bd.chia.dto.WalletTransactions;
import com.google.gson.Gson;

@Component
@Profile({"local"})
public class WalletTrackingRPCLocal implements IWalletTrackingRPC {
	public WalletTransactions getWalletTransactions() throws Exception {
		List<String> args = new ArrayList<String>();
		args.add("D:\\Tools\\curl-7.78.0-win64-mingw\\bin\\curl.exe");
		args.add("--insecure");
		args.add("--cert");
		args.add("C:\\Users\\nhat\\.chia\\mainnet\\config\\ssl\\ui\\boston-pool-node\\wallet\\private_wallet.crt");
		args.add("--key");
		args.add("C:\\Users\\nhat\\.chia\\mainnet\\config\\ssl\\ui\\boston-pool-node\\wallet\\private_wallet.key");
		args.add("-d");
		args.add("\"{\\\"wallet_id\\\": 1}\"");
		args.add("-H");
		args.add("Content-Type: application/json");
		args.add("-X");
		args.add("POST");
		args.add("https://192.168.99.9:9256/get_transactions");		
		

		ProcessBuilder pb = new ProcessBuilder(args);
		
		Gson gson = new Gson();
		WalletTransactions wt = gson.fromJson(new InputStreamReader(pb.start().getInputStream()), WalletTransactions.class);
		return wt;
	}
}

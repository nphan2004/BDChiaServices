package com.bd.chia.services;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bd.chia.dto.WalletTransactions;
import com.google.gson.Gson;

@Component
public class WalletTrackingRPC implements IWalletTrackingRPC {
	private static final Logger log = LoggerFactory.getLogger(WalletTrackingRPC.class);
	
    @Value("${curl.path}")
    private String curlExecutable;
    
    @Value("${curl.certificate}")
    private String certificate;
    
    @Value("${curl.key}")
    private String key;
    
    @Value("${curl.url}")
    private String url;
    
    @Value("${curl.data}")
    private String data;
    
	@Override
	public WalletTransactions getWalletTransactions() throws Exception {
		List<String> args = new ArrayList<String>();
		args.add(curlExecutable);
		args.add("--verbose");
		args.add("--insecure");
		args.add("--cert");
		args.add(certificate);
		args.add("--key");
		args.add(key);
		args.add("-d");
		args.add(data.trim());
		args.add("-H");
		args.add("Content-Type: application/json");
		args.add("-X");
		args.add("POST");
		args.add(url);				
		
		ProcessBuilder pb = new ProcessBuilder(args);
		
		Gson gson = new Gson();
		
		WalletTransactions wt = gson.fromJson(new InputStreamReader(pb.start().getInputStream()), WalletTransactions.class);

		return wt;
	}
}

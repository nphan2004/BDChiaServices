package com.bd.chia.services;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bd.chia.dto.NodeInfo;
import com.google.gson.Gson;

@Component
public class NodeRPC {
	private static final Logger log = LoggerFactory.getLogger(WalletTrackingRPC.class);
	
    @Value("${curl.path}")
    private String curlExecutable;
    
    @Value("${get_blockchain_state.certificate}")
    private String certificate;
    
    @Value("${get_blockchain_state.key}")
    private String key;
    
    @Value("${get_blockchain_state.url}")
    private String url;
    
    @Value("${get_blockchain_state.data}")
    private String data;
    
	public NodeInfo getBlockChainState() throws Exception {
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
		
		NodeInfo nodeInfo = gson.fromJson(new InputStreamReader(pb.start().getInputStream()), NodeInfo.class);

		return nodeInfo;
	}
}

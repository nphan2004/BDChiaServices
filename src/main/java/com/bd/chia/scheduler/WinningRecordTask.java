package com.bd.chia.scheduler;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bd.chia.dto.WalletTransactions;
import com.bd.chia.dto.WalletTransactions.AWalletTransaction;
import com.bd.chia.jpa.WinningRecord;
import com.bd.chia.repository.WinningRecordRepository;
import com.bd.chia.services.IWalletTrackingRPC;

@Profile("enableSchedule")
@Component
public class WinningRecordTask {
	private static final Logger log = LoggerFactory.getLogger(WinningRecordTask.class);
	
	@Autowired
	WinningRecordRepository winningRecordRepository;
	
	@Autowired
	IWalletTrackingRPC rpc;
		
	@Scheduled(fixedDelay=300000, initialDelay=1000)
	public void updateWinningData() {
		try {			
			WalletTransactions wt = rpc.getWalletTransactions();
			if(wt!=null) {
				for(AWalletTransaction tx : wt.getTransactions()) {
					Optional<WinningRecord> oTx = winningRecordRepository.findById(tx.getConfirmedAtHeight());
					if(!oTx.isPresent()) {
						WinningRecord record = new WinningRecord();
						record.setConfirmedAtHeight(tx.getConfirmedAtHeight());
						record.setAmount(tx.getAmount());
						record.setConfirmed(tx.getConfirmed());
						record.setCreatedAt(tx.getCreatedAt());
						record.setFeeAmount(tx.getFeeAmount());
						record.setName(tx.getName());
						record.setToAddress(tx.getToAddress());
						record.setType(tx.getType());
						
						winningRecordRepository.save(record);
					}
				}
			} else {
				log.warn("Curl to RPC failed.");
			}
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}

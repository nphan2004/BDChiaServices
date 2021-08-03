package com.bd.chia.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bd.chia.dto.WalletTransactions;
import com.bd.chia.dto.WalletTransactions.AWalletTransaction;
import com.bd.chia.dto.WalletTransactions.AnAddition;
import com.bd.chia.jpa.Farmer;
import com.bd.chia.jpa.Payout;
import com.bd.chia.jpa.WinningRecord;
import com.bd.chia.repository.FarmerRepository;
import com.bd.chia.repository.PayoutRepository;
import com.bd.chia.repository.WinningRecordRepository;
import com.bd.chia.services.IWalletTrackingRPC;

@Profile("enableSchedule")
@Component
public class WinningRecordTask {
	private static final Logger log = LoggerFactory.getLogger(WinningRecordTask.class);
	private static final Integer INCOMING_TX = 0;
	private static final Integer OUTGOING_TX = 1;
	private static Map<Long, Boolean> alreadyProcessed = new HashMap<Long, Boolean>();
	
	@Autowired
	WinningRecordRepository winningRecordRepository;
	
	@Autowired
	PayoutRepository payoutRepository;
	
	@Autowired
	FarmerRepository farmerRepository;
	
	@Autowired
	IWalletTrackingRPC rpc;
	
	private final void addWinningRecord(AWalletTransaction tx) {
		synchronized(alreadyProcessed) {
			if(!alreadyProcessed.containsKey(tx.getConfirmedAtHeight())) {
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
				
				alreadyProcessed.put(tx.getConfirmedAtHeight(), Boolean.TRUE);
			}
		}		
	}
	
	private final void addPayoutRecord(AWalletTransaction tx) {
		synchronized(alreadyProcessed) {
			if(!alreadyProcessed.containsKey(tx.getConfirmedAtHeight())) {
				for(AnAddition a : tx.getAdditions()) {
					Farmer farmer = farmerRepository.findByPayoutInstructions(a.getPuzzleHash().substring(2)); //skip 0x
					if(farmer==null) {
						continue; //no farmer found.
					}
					Payout payout = payoutRepository.findByLauncherIdAndConfirmedAtHeight(farmer.getLauncherId(), tx.getConfirmedAtHeight());
					if(payout==null) {
						payout = new Payout();
						payout.setLauncherId(farmer.getLauncherId());
						payout.setConfirmedAtHeight(tx.getConfirmedAtHeight());
						payout.setAmount(a.getAmount());
						payout.setParentCoin(a.getParentCoinInfo());
						payout.setPuzzleHash(a.getPuzzleHash());
						payout.setPayDate(tx.getCreatedAt());
						
						payoutRepository.save(payout);
					}
				}

				alreadyProcessed.put(tx.getConfirmedAtHeight(), Boolean.TRUE);
			}
		}
	}
	
	@Scheduled(fixedDelay=300000, initialDelay=1000)
	public void updateWinningAndPayoutData() {
		try {			
			WalletTransactions wt = rpc.getWalletTransactions();
			if(wt!=null) {
				for(AWalletTransaction tx : wt.getTransactions()) {
					Integer txType = tx.getType();
					
					if(txType==INCOMING_TX) {
						addWinningRecord(tx);
					} else if(txType==OUTGOING_TX) {
						addPayoutRecord(tx);
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

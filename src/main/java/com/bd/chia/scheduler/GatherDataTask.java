package com.bd.chia.scheduler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bd.chia.dto.NodeInfo;
import com.bd.chia.dto.WalletTransactions;
import com.bd.chia.dto.WalletTransactions.AWalletTransaction;
import com.bd.chia.dto.WalletTransactions.AnAddition;
import com.bd.chia.jpa.FarmStats;
import com.bd.chia.jpa.Farmer;
import com.bd.chia.jpa.Payout;
import com.bd.chia.jpa.PoolInformation;
import com.bd.chia.jpa.PoolInformation.PoolSpaceTime;
import com.bd.chia.jpa.WinningRecord;
import com.bd.chia.repository.FarmStatsRepository;
import com.bd.chia.repository.FarmerRepository;
import com.bd.chia.repository.PartialRepository;
import com.bd.chia.repository.PayoutRepository;
import com.bd.chia.repository.PoolInformationRepository;
import com.bd.chia.repository.WinningRecordRepository;
import com.bd.chia.services.NodeRPC;
import com.bd.chia.services.TelegramBotService;
import com.bd.chia.services.WalletTrackingRPC;
import com.bd.chia.utils.Constants;

@Profile("enableSchedule")
@Component
public class GatherDataTask {
	private static final Logger log = LoggerFactory.getLogger(GatherDataTask.class);
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
	PoolInformationRepository poolInformationRepository;
	
	@Autowired
	FarmStatsRepository farmStatsRepository;
	
	@Autowired
	PartialRepository partialRepository;
	
	@Autowired
	WalletTrackingRPC rpc;
	
	@Autowired
	NodeRPC nodeRPC;
	
	@Autowired
	TelegramBotService telegramBotService;
	
	private final boolean addWinningRecord(AWalletTransaction tx) {
		boolean newWinning = false;
		
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
					newWinning = true;
				}
				
				alreadyProcessed.put(tx.getConfirmedAtHeight(), Boolean.TRUE);
			}
		}
		
		return newWinning;
	}
	
	private final void addPayoutRecord(AWalletTransaction tx) {
		synchronized(alreadyProcessed) {
			if(!alreadyProcessed.containsKey(tx.getConfirmedAtHeight())) {
				for(AnAddition a : tx.getAdditions()) {
					List<Farmer> farmers = farmerRepository.findByPayoutInstructions(a.getPuzzleHash().substring(2)); //skip 0x
					if(farmers==null || farmers.isEmpty()) {
						continue; //no farmer found.
					}
					Farmer farmer = farmers.get(0);
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
				int winningCount = 0;
				for(AWalletTransaction tx : wt.getTransactions()) {
					Integer txType = tx.getType();
					
					if(txType==INCOMING_TX) {
						if(addWinningRecord(tx)) {
							winningCount++;
						}
					} else if(txType==OUTGOING_TX) {
						addPayoutRecord(tx);
					}
				}
				if(winningCount > 0) {
					telegramBotService.sendNotification("Vast Pool just hit " + winningCount + " block");
				}
			} else {
				log.warn("Curl to RPC failed.");
			}
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Scheduled(fixedDelay=310000, initialDelay=5000)
	public void updatePoolInfo() {
		try {
			Optional<PoolInformation> oPoolInfo = poolInformationRepository.findById(PoolInformation.ID);
			PoolInformation pi = oPoolInfo.isPresent() ? oPoolInfo.get() : new PoolInformation();
			
			pi.setUpdateTime(new Date());
			pi.setFarmerOnline(farmerRepository.countByPoolMember(1));
			pi.setBlockFound(winningRecordRepository.countByType(WinningRecord.INCOMING));
			pi.setPoolTotalPoints(farmerRepository.totalPoints());
			poolInformationRepository.save(pi);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	public void newDayUpdate() {
		//create a history record for pool space.
		Optional<PoolInformation> oPoolInfo = poolInformationRepository.findById(PoolInformation.ID);
		PoolInformation pi = oPoolInfo.isPresent() ? oPoolInfo.get() : new PoolInformation();
		PoolSpaceTime poolSpaceTime = new PoolSpaceTime();
		poolSpaceTime.setDate(new Date());
		poolSpaceTime.setPoolSpace(pi.getPoolSpace());
		poolSpaceTime.setPoolSpaceRaw(pi.getPoolSpaceRaw());
		poolSpaceTime.setPoolSpaceUnit(pi.getPoolSpaceUnit());
		
		pi.addPoolSpaceHistory(poolSpaceTime);
		poolInformationRepository.save(pi);
	}
	
	@Scheduled(cron = "0 0 */1 * * *")
	public void updatePoolInfoLong() {
		try {
			NodeInfo ni = nodeRPC.getBlockChainState();
			Optional<PoolInformation> oPoolInfo = poolInformationRepository.findById(PoolInformation.ID);
			PoolInformation pi = oPoolInfo.isPresent() ? oPoolInfo.get() : new PoolInformation();
			
			pi.setUpdateTime(new Date());
			pi.setNetDifficulty(ni.getBlockChainstate().getDifficulty());
			pi.setNetspaceRaw(ni.getBlockChainstate().getSpace());
			
			List<Farmer> members = farmerRepository.findByPoolMember(1);
			Integer totalDifficulty = 0;
			for(Farmer farmer : members) {
				Optional<FarmStats> ostats = farmStatsRepository.findById(farmer.getLauncherId());
				FarmStats fstats = null;
				if(ostats.isPresent()) {
					fstats = ostats.get();
					fstats.newEstimateSize(farmer.getDifficulty());
				} else {
					fstats = new FarmStats();
					fstats.setLauncherId(farmer.getLauncherId());
					fstats.newEstimateSize(farmer.getDifficulty());
				}
				
				farmStatsRepository.save(fstats);
				totalDifficulty += farmer.getDifficulty();
			}
			
			pi.setPoolSpaceRaw(new BigDecimal(totalDifficulty).multiply(new BigDecimal(Constants.PLOTS_SIZE_MULTIPLIER)).multiply(new BigDecimal(Constants.PLOTS_SIZE)));
			poolInformationRepository.save(pi);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}		
	}
	
	@Scheduled(fixedDelay=3600000, initialDelay=5000)
	public void cleanupPartial() {
		//Keep 30 hours of partial
		log.info("Delete " + partialRepository.deleteOlderPartial(30) + " partial records.");
	}
}

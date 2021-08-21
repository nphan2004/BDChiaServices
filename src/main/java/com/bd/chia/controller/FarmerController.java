package com.bd.chia.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bd.chia.jpa.FarmStats;
import com.bd.chia.jpa.Farmer;
import com.bd.chia.jpa.Payout;
import com.bd.chia.repository.FarmStatsRepository;
import com.bd.chia.repository.FarmerRepository;
import com.bd.chia.repository.FarmerRepositoryImpl.FarmerStats;
import com.bd.chia.repository.PayoutRepository;
import com.bd.chia.utils.Constants;
import com.bd.chia.utils.HelperUtil;

@RestController
@RequestMapping(path = "/farmer")
public class FarmerController {
	public static final int ONE_DAY = 24 * 60 * 60 * 1000;
	
	@Autowired
	FarmerRepository farmerRepository;
	
	@Autowired
	PayoutRepository payoutRepository;
	
	@Autowired
	FarmStatsRepository farmStatsRepository;
	
	static class EarningPeriod {
		private String period;
		private Long amount;
		
		public EarningPeriod() {}
		public EarningPeriod(String period, Long amount) {
			this.period = period;
			this.amount = amount;
		}
		
		public String getPeriod() {
			return period;
		}
		public void setPeriod(String period) {
			this.period = period;
		}
		public Long getAmount() {
			return amount;
		}
		public void setAmount(Long amount) {
			this.amount = amount;
		}
	}
	
	@GetMapping(value="/{launcherId}")
	@ResponseBody
	public ResponseEntity<Farmer> getFarmer(@PathVariable String launcherId) {
		
		Farmer farmer =  farmerRepository.findByLauncherId(HelperUtil.remove0x(launcherId));
		if(farmer!=null) {
			return ResponseEntity.ok(farmer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value="/leaderboard")
	@ResponseBody
	public List<FarmerStats> getFarmers() {
		return farmerRepository.getLeaderBoard();
	}
	
	@GetMapping(value="/payout/{launcherId}")
	@ResponseBody
	public List<Payout> getPayout(@PathVariable String launcherId) {
				return payoutRepository.findByLauncherIdOrderByConfirmedAtHeightDesc(HelperUtil.remove0x(launcherId));
	}
	
	@GetMapping(value="/stats/{launcherId}")
	@ResponseBody
	public ResponseEntity<FarmStats> getFarmStats(@PathVariable String launcherId) {
		
		Optional<FarmStats> ostats =  farmStatsRepository.findById(HelperUtil.remove0x(launcherId));
		if(ostats.isPresent()) {
			return ResponseEntity.ok(ostats.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	private static Map<Long, Long> createEarningMap() {
		Map<Long, Long> earning = new HashMap<Long, Long>();
		earning.put(Constants.HOUR_12, 0L);
		earning.put(Constants.HOUR_24, 0L);
		earning.put(Constants.DAY_7, 0L);
		earning.put(Constants.DAY_30, 0L);
		
		return earning;
	}
	
	private static void calculateEarning(Map<Long, Long> map, Payout payout, long currentTime) {
		long timeRange = currentTime - payout.getPayDate().getTime();
		if(timeRange < Constants.HOUR_12) {
			map.put(Constants.HOUR_12, map.get(Constants.HOUR_12) + payout.getAmount());
			map.put(Constants.HOUR_24, map.get(Constants.HOUR_24) + payout.getAmount());
			map.put(Constants.DAY_7, map.get(Constants.DAY_7) + payout.getAmount());
			map.put(Constants.DAY_30, map.get(Constants.DAY_30) + payout.getAmount());
		} else if(timeRange < Constants.HOUR_24) {
			map.put(Constants.HOUR_24, map.get(Constants.HOUR_24) + payout.getAmount());
			map.put(Constants.DAY_7, map.get(Constants.DAY_7) + payout.getAmount());
			map.put(Constants.DAY_30, map.get(Constants.DAY_30) + payout.getAmount());
		} else if(timeRange < Constants.DAY_7) {
			map.put(Constants.DAY_7, map.get(Constants.DAY_7) + payout.getAmount());
			map.put(Constants.DAY_30, map.get(Constants.DAY_30) + payout.getAmount());			
		} else if(timeRange < Constants.DAY_30) {
			map.put(Constants.DAY_30, map.get(Constants.DAY_30) + payout.getAmount());
		}
	}
	
	private static List<EarningPeriod> convertFriendlyName(Map<Long, Long> earning) {
		List<EarningPeriod> l = new ArrayList<EarningPeriod>();
		
		l.add(new EarningPeriod("12 Hours", earning.get(Constants.HOUR_12)));		
		l.add(new EarningPeriod("24 Hours", earning.get(Constants.HOUR_24)));
		l.add(new EarningPeriod("7 Days", earning.get(Constants.DAY_7)));
		l.add(new EarningPeriod("30 Days", earning.get(Constants.DAY_30)));
		
		return l;
	}
	
	@GetMapping(value="/earning/{launcherId}")
	@ResponseBody	
	public List<EarningPeriod> getEarning(@PathVariable String launcherId) {
		Map<Long, Long> earning = createEarningMap();

		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DATE, -30);
		List<Payout> payouts = payoutRepository.findByLauncherIdAndPayDateGreaterThanEqualOrderByPayDateDesc(HelperUtil.remove0x(launcherId), cl.getTime());
		
		long currentTimeMs = System.currentTimeMillis();
		for(Payout payout : payouts) {
			calculateEarning(earning, payout, currentTimeMs);
		}
		
		return convertFriendlyName(earning);
	}
}

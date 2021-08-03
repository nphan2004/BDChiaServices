package com.bd.chia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bd.chia.jpa.Farmer;
import com.bd.chia.jpa.Payout;
import com.bd.chia.repository.FarmerRepository;
import com.bd.chia.repository.FarmerRepositoryImpl.FarmerStats;
import com.bd.chia.repository.PayoutRepository;

@RestController
@RequestMapping(path = "/farmer")
public class FarmerController {
	@Autowired
	FarmerRepository farmerRepository;
	
	@Autowired
	PayoutRepository payoutRepository;
	
	@GetMapping(value="/{launcherId}")
	@ResponseBody
	public Farmer getFarmer(@PathVariable String launcherId) {
		
		Optional<Farmer> o =  farmerRepository.findById(launcherId);
		if(o.isPresent()) {
			return o.get();
		}
		
		return null;
	}
	
	@GetMapping(value="/leaderboard")
	@ResponseBody
	public List<FarmerStats> getFarmers() {
		return farmerRepository.getLeaderBoard();
	}
	
	@GetMapping(value="/payout/{launcherId}")
	@ResponseBody
	public List<Payout> getPayout(@PathVariable String launcherId) {
				return payoutRepository.findByLauncherIdOrderByConfirmedAtHeightDesc(launcherId);
	}	
}

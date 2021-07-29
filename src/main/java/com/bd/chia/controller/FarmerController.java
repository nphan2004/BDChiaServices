package com.bd.chia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bd.chia.jpa.Farmer;
import com.bd.chia.repository.FarmerRepository;

@RestController
@RequestMapping(path = "/farmer")
public class FarmerController {
	@Autowired
	FarmerRepository farmerRepository;
	
	@RequestMapping(value="/{launcherId}", method = RequestMethod.GET)
	@ResponseBody
	public Farmer getFarmer(@PathVariable String launcherId) {
		
		Optional<Farmer> o =  farmerRepository.findById(launcherId);
		if(o.isPresent()) {
			return o.get();
		}
		
		return null;
	}
}

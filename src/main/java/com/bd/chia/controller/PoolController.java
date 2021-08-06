package com.bd.chia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bd.chia.jpa.PoolInformation;
import com.bd.chia.repository.PoolInformationRepository;

@RestController
@RequestMapping(path = "/pool")
public class PoolController {
	@Autowired
	PoolInformationRepository poolInformationRepository;
	
	@GetMapping(value="/info")
	@ResponseBody
	public PoolInformation getPoolInformation() {
		Optional<PoolInformation> op = poolInformationRepository.findById(PoolInformation.ID);
		return (op.isPresent() ? op.get() : null);
	}
}

package com.bd.chia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bd.chia.jpa.Farmer;

public interface FarmerRepository extends CrudRepository<Farmer, String>, FarmerRepositoryCustom {
	Farmer findByLauncherId(String launcherId);
	List<Farmer> findByPayoutInstructions(String payoutInstructions);
	long countByPoolMember(Integer poolMember);	
	List<Farmer> findByPoolMember(Integer poolMember);
}

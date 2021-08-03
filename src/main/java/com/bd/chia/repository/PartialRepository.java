package com.bd.chia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bd.chia.jpa.Partial;


public interface PartialRepository extends MongoRepository<Partial, String>, PartialRepositoryCustom {
	List<Partial> findByLauncherIdOrderByTimestampDesc(String launcherId);
}

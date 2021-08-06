package com.bd.chia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bd.chia.jpa.PoolInformation;

public interface PoolInformationRepository extends MongoRepository<PoolInformation, String> {

}

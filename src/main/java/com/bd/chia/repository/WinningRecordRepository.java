package com.bd.chia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bd.chia.jpa.WinningRecord;

public interface WinningRecordRepository extends MongoRepository<WinningRecord, Long> {
	List<WinningRecord> findAllByType(Integer type);
	int countByType(Integer type);
}

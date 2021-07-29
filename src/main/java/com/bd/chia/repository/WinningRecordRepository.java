package com.bd.chia.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.bd.chia.jpa.WinningRecord;

@EnableScan
public interface WinningRecordRepository extends CrudRepository<WinningRecord, Long> {
	List<WinningRecord> findAll();
}

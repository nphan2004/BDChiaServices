package com.bd.chia.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bd.chia.jpa.Payout;

public interface PayoutRepository extends MongoRepository<Payout, String>, PayoutRepositoryCustom {
	Payout findByLauncherIdAndConfirmedAtHeight(String launcherId, Long confirmedAtHeight);
	List<Payout> findByLauncherIdOrderByConfirmedAtHeightDesc(String launcherId);
	List<Payout> findByLauncherIdAndPayDateGreaterThanEqualOrderByPayDateDesc(String launcherId, Date payDate);
}

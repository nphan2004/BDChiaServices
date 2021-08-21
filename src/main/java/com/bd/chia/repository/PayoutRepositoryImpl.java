package com.bd.chia.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bd.chia.jpa.Payout;

public class PayoutRepositoryImpl implements PayoutRepositoryCustom {
	@Autowired
	MongoTemplate mongoTemplate;
	
//	@Override
//	public List<Payout> findByLauncherIdForLastXDays(Integer days) {
//		Query query = new Query();
//		long xSecondsAgo = (System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000)) / 1000;
//
//		query.addCriteria(Criteria.where("payDate").gte(xSecondsAgo)).getSortObject().;
//		
//		return mongoTemplate.find(query, Payout.class);
//	}
}

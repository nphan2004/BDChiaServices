package com.bd.chia.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bd.chia.jpa.Partial;

public class PartialRepositoryImpl implements PartialRepositoryCustom {
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Long deleteOlderPartial(int hoursOld) {
		Query query = new Query();
		long deleteTimeInSeconds = (System.currentTimeMillis() - (hoursOld * 60 * 60 * 1000)) / 1000;

		query.addCriteria(Criteria.where("timestamp").lt(deleteTimeInSeconds));
		
		return mongoTemplate.remove(query, Partial.class).getDeletedCount();
	}
}

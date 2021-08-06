package com.bd.chia.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bd.chia.jpa.Farmer;

public class FarmerRepositoryImpl implements FarmerRepositoryCustom {
	@Autowired
	MongoTemplate mongoTemplate;
	
	public class FarmerStats {
		private String launcherId;
		private Integer points;		
		private Integer difficulty;
		
		public FarmerStats(Farmer farmer) {
			this.launcherId = farmer.getLauncherId();
			this.points = farmer.getPoints();
			this.difficulty = farmer.getDifficulty();
		}
		
		public String getLauncherId() {
			return launcherId;
		}
		public void setLauncherId(String launcherId) {
			this.launcherId = launcherId;
		}
		public Integer getPoints() {
			return points;
		}
		public void setPoints(Integer points) {
			this.points = points;
		}
		public Integer getDifficulty() {
			return difficulty;
		}
		public void setDifficulty(Integer difficulty) {
			this.difficulty = difficulty;
		}
	}
	
	@Override
	public List<FarmerStats> getLeaderBoard() {
		Query query = new Query();
		query.addCriteria(Criteria.where("is_pool_member").is(1));
		query.with(Sort.by(Direction.DESC, "points"));
		query.limit(100);
		
		List<Farmer> farmers = mongoTemplate.find(query, Farmer.class);
		List<FarmerStats> result = new ArrayList<FarmerStats>();
		
		//Don't send back confidential information
		for(Farmer farmer : farmers) {
			result.add(new FarmerStats(farmer));
		}
		return result;
	}
}

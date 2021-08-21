package com.bd.chia.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bd.chia.jpa.Farmer;
import com.bd.chia.utils.Constants;

public class FarmerRepositoryImpl implements FarmerRepositoryCustom {
	@Autowired
	MongoTemplate mongoTemplate;
	
	public class FarmerStats {
		private String launcherId;
		private Integer points;		
		private Integer difficulty;
		private Integer plots;
		private String name;
		
		public FarmerStats(Farmer farmer) {
			this.launcherId = farmer.getLauncherId();
			this.points = farmer.getPoints();
			this.difficulty = farmer.getDifficulty();
			this.plots = this.difficulty * Constants.PLOTS_SIZE_MULTIPLIER;
			this.name = farmer.getName();
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

		public Integer getPlots() {
			return plots;
		}

		public void setPlots(Integer plots) {
			this.plots = plots;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

	public static class Total {
		String _id;
		Long total;
		public String get_id() {
			return _id;
		}
		public void set_id(String _id) {
			this._id = _id;
		}
		public Long getTotal() {
			return total;
		}
		public void setTotal(Long total) {
			this.total = total;
		}
	}
	
	@Override
	public Long totalPoints() {

		Aggregation aggregation = newAggregation(match(Criteria.where("poolMember").is(1)), group("poolMember").sum("points").as("total"));
		AggregationResults<Total> groupResults = mongoTemplate.aggregate(aggregation, Farmer.class, Total.class);
		
		Total total = groupResults.getUniqueMappedResult();
		
		return total.getTotal();
	}
}

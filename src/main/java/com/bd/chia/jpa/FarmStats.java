package com.bd.chia.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bd.chia.utils.Constants;

@Document
public class FarmStats {
	public static class EstimatedSize {		
		private Date timestamp;
		private Integer difficulty;
		private Integer plots;
		
		public Date getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
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
	}
	
	@Id
	private String launcherId;
	private List<EstimatedSize> estimates;
	private Integer difficulty;
	private Integer plots;
	
	public String getLauncherId() {
		return launcherId;
	}
	public void setLauncherId(String launcherId) {
		this.launcherId = launcherId;
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
	public List<EstimatedSize> getEstimates() {
		return estimates;
	}
	public void setEstimates(List<EstimatedSize> estimates) {
		this.estimates = estimates;
	}
	public void newEstimateSize(Integer difficulty) {
		this.difficulty = difficulty;
		this.plots = difficulty * Constants.PLOTS_SIZE_MULTIPLIER;
		
		EstimatedSize es = new EstimatedSize();
		es.setDifficulty(difficulty);
		es.setPlots(this.plots);
		es.setTimestamp(new Date());
		
		if(this.estimates==null) {
			this.estimates = new ArrayList<EstimatedSize>();
		}
		
		this.estimates.add(es);
		
		while(this.estimates.size() > 12) {
			this.estimates.remove(0); //remove the oldest stats
		}
	}
}

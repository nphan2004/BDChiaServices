package com.bd.chia.jpa;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "partial")
public class Partial {
    @DynamoDBDocument
	public static class PrimaryKey {
        @DynamoDBHashKey 
        @DynamoDBAttribute(attributeName = "launcher_id")
		private String launcherId;
        
        @DynamoDBRangeKey
        @DynamoDBAttribute(attributeName = "timestamp")        
		private Long timestamp;
                
		public String getLauncherId() {
			return launcherId;
		}
		public void setLauncherId(String launcherId) {
			this.launcherId = launcherId;
		}
		public Long getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}
	}
    
	@Id
    @DynamoDBIgnore
    private PrimaryKey primaryKey;	
    private Integer difficulty;
    
    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "launcher_id")
    public String getLauncher_id() {
    	return primaryKey != null ? primaryKey.getLauncherId() : null;
    }
    
    public void setLauncher_id(String launcherId) {
    	if(primaryKey == null) {
    		primaryKey = new PrimaryKey();
    	}
    	
    	primaryKey.setLauncherId(launcherId);
    }

	@DynamoDBRangeKey
	@DynamoDBAttribute(attributeName = "timestamp")		
	public Long getTimestamp() {
		return primaryKey != null ? primaryKey.getTimestamp() : null;
	}

	public void setTimestamp(Long timestamp) {
    	if(primaryKey == null) {
    		primaryKey = new PrimaryKey();
    	}
    	
    	primaryKey.setTimestamp(timestamp);	
    }

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "Partial [launcher_id:" + getLauncher_id() + ", timestamp : " + getTimestamp() + ", difficulty=" + difficulty + "]";
	}
	
	
}

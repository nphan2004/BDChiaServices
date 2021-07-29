package com.bd.chia.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.bd.chia.jpa.Partial;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PartialRepositoryImpl implements PartialRepositoryCustom {
	@Autowired
	AmazonDynamoDB client;
	
	@Override
	public List<Partial> findByLauncherId(String launcherId) {
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.getTable("partial");
		
		QuerySpec spec = new QuerySpec()
			.withKeyConditionExpression("launcher_id = :launcher_id")
			.withScanIndexForward(false)
			.withValueMap(new ValueMap().withString(":launcher_id", launcherId));
		
		ItemCollection<QueryOutcome> items = table.query(spec);
		
		List<Partial> partials = new ArrayList<Partial>();
		Iterator<Item> iterator = items.iterator();
		
		ObjectMapper mapper = new ObjectMapper();
		while(iterator.hasNext()) {
			partials.add(mapper.convertValue(iterator.next().asMap(), Partial.class));
		}
		
		return partials;
	}
}

package com.bd.chia.repository;

public class PartialRepositoryImpl implements PartialRepositoryCustom {
//
//	@Override
//	public List<Partial> findByLauncherId(String launcherId) {
//		DynamoDB dynamoDB = new DynamoDB(client);
//		Table table = dynamoDB.getTable("partial");
//		
//		QuerySpec spec = new QuerySpec()
//			.withKeyConditionExpression("launcher_id = :launcher_id")
//			.withScanIndexForward(false)
//			.withValueMap(new ValueMap().withString(":launcher_id", launcherId));
//		
//		ItemCollection<QueryOutcome> items = table.query(spec);
//		
//		List<Partial> partials = new ArrayList<Partial>();
//		Iterator<Item> iterator = items.iterator();
//		
//		ObjectMapper mapper = new ObjectMapper();
//		while(iterator.hasNext()) {
//			partials.add(mapper.convertValue(iterator.next().asMap(), Partial.class));
//		}
//		
//		return partials;
//	}
}

package com.bd.chia.utils;

import java.io.FileReader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import com.bd.chia.jpa.Farmer;
import com.bd.chia.repository.FarmerRepository;

@Component
public class FarmerDataImport {
	@Autowired
	FarmerRepository farmerRepository;
	
	private static CellProcessor[] getProcessors() {
		final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(),
				new NotNull(),
				new NotNull(),
				new ParseLong(),
				new ParseInt(),
				new ParseBool(),
				new NotNull(),
				new NotNull(),
				new ParseInt(),
				new NotNull(),
				new NotNull()
		};
		
		return processors;
	}
	
	public void importData() throws Exception {
		ICsvMapReader mapReader = new CsvMapReader(new FileReader("C:\\Users\\nhat\\Downloads\\farmer.csv"), CsvPreference.STANDARD_PREFERENCE);
		final String[] headers = mapReader.getHeader(true);
		
		final CellProcessor[] processors = getProcessors();
		Map<String, Object> partialMap;
		while( (partialMap = mapReader.read(headers, processors)) != null) {
			Farmer farmer = new Farmer();
			farmer.setLauncherId((String)partialMap.get("launcher_id (S)"));
			farmer.setDelay_time((Long)partialMap.get("delay_time (N)"));
			farmer.setDifficulty((Integer) partialMap.get("difficulty (N)"));
			farmer.setIs_pool_member((Boolean)partialMap.get("is_pool_member (N)"));
			farmer.setP2_singleton_puzzle_hash((String)partialMap.get("p2_singleton_puzzle_hash (S)"));
			farmer.setPayout_instructions((String)partialMap.get("payout_instructions (S)"));
			farmer.setPoints((Integer) partialMap.get("points (N)"));
			farmerRepository.save(farmer);
		}
	}
}

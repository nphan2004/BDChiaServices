package com.bd.chia.utils;

import java.io.FileReader;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import com.bd.chia.jpa.Partial;
import com.bd.chia.repository.PartialRepository;

@Component
public class PartialDataImport {
	@Autowired
	PartialRepository partialRepository;
	
	private static CellProcessor[] getProcessors() {
		final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(),
				new ParseLong(),
				new ParseInt()
		};
		
		return processors;
	}
	
	//@PostConstruct
	public void importData() throws Exception {
		ICsvMapReader mapReader = new CsvMapReader(new FileReader("C:\\Users\\nhat\\Downloads\\partial.csv"), CsvPreference.STANDARD_PREFERENCE);
		final String[] headers = mapReader.getHeader(true);
		
		final CellProcessor[] processors = getProcessors();
		Map<String, Object> partialMap;
		while( (partialMap = mapReader.read(headers, processors)) != null) {
			Partial partial = new Partial();
			partial.setLauncherId((String)partialMap.get("launcher_id (S)"));
			partial.setTimestamp((Long)partialMap.get("timestamp (N)"));
			partial.setDifficulty((Integer) partialMap.get("difficulty (N)"));
						
			partialRepository.save(partial);
		}
	}
}

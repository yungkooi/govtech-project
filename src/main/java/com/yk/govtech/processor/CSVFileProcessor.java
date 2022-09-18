package com.yk.govtech.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.yk.govtech.entity.User;

public class CSVFileProcessor {
	
	public static boolean isValidCSVFile(MultipartFile file) {
		
		boolean status = "text/csv".equalsIgnoreCase(file.getContentType()) ? true : false; 
		
		return status;
	}
	
	public static List<User> processCSVFile(InputStream inputStream) {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
				List<User> users = new ArrayList<User>();
				Iterable<CSVRecord> csvRecords = csvParser.getRecords();
				for (CSVRecord csvRecord : csvRecords) {
					User user = new User(csvRecord.get("Name"), Double.parseDouble(csvRecord.get("salary")));
					if (user.getSalary() >= 0) {
						users.add(user);
					}
				}
				return users;
		} catch (IOException e) {
			throw new RuntimeException("Unable to parse CSV file: " + e.getMessage());
		}
	}

}

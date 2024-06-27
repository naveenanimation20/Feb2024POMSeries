package com.qa.opencart.utils;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtil {

	private static final String CSV_PATH = "./src/test/resources/testdata/";

	private static List<String[]> rows;

	public static Object[][] csvData(String csvName) {
		String csvFile = CSV_PATH + csvName + ".csv";

		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(csvFile));
			rows = reader.readAll();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}

		Object[][] data = new Object[rows.size()][];

		for (int i = 0; i < rows.size(); i++) {
			data[i] = rows.get(i);
		}

		return data;
	}
}



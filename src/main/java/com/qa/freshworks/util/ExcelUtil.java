package com.qa.freshworks.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static FileInputStream fip;
	public static Workbook book;
	public static Sheet sheet;

	public static String TEST_DATA_SHEET_PATH = "./src/main/java/com/qa/freshworks/testdata/Freshworks.xlsx";

	public static Object[][] getTestData(String sheetName) {

		Object[][] testData = null;

		try {
			fip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(fip);
			sheet = book.getSheet(sheetName);

			testData = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
					testData[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return testData;

	}

}

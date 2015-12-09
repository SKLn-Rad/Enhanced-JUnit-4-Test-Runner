package com.rysource.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * INTERNAL USE ONLY.
 * The Apache POI Excel parser for test results.
 * 
 * @author ryandixon1993@gmail.com
 */
public class ExcelReport {

	/*
	 * Styles
	 */
	private static XSSFCellStyle headerStyle;
	private static XSSFCellStyle tableHeaderStyle;
	
	public static void generateExcelReport(File file) {
		Workbook report = new XSSFWorkbook();
		getStyles(report);
		generateExcelHeader(report);
		generateExcelTestData(report);
		
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			report.write(fileOut);
			fileOut.close();
			System.out.println("Successfully wrote report to disk.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void getStyles(Workbook report) {
		// Header Style
		headerStyle = (XSSFCellStyle) report.createCellStyle();
		XSSFFont headerFont = (XSSFFont) report.createFont();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		headerStyle.setAlignment(HorizontalAlignment.LEFT);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		headerFont.setBold(false);
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerStyle.setFont(headerFont);
		
		// Table Header Style
		tableHeaderStyle = (XSSFCellStyle) report.createCellStyle();
		XSSFFont tableHeaderFont = (XSSFFont) report.createFont();
		tableHeaderStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		tableHeaderStyle.setAlignment(HorizontalAlignment.LEFT);
		tableHeaderStyle.setBorderBottom(BorderStyle.THIN);
		tableHeaderStyle.setBorderLeft(BorderStyle.THIN);
		tableHeaderStyle.setBorderRight(BorderStyle.THIN);
		tableHeaderStyle.setBorderTop(BorderStyle.THIN);
		tableHeaderFont.setColor(IndexedColors.BLACK.getIndex());
		tableHeaderFont.setBold(true);
		tableHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeaderStyle.setFont(tableHeaderFont);
	}
	
	private static void generateExcelTestData(Workbook report) {
		Iterator<Entry<String, TestSuite>> iter = ReportGenerator.SUITES.entrySet().iterator();
		
		while (iter.hasNext()) {
			TestSuite suite = iter.next().getValue();
			System.out.println("Adding sheet for: " + suite.getName());
			
			Sheet suiteSheet = report.createSheet(suite.getName());
			int row = 1;
			
			Row suiteName = suiteSheet.createRow((short) row);
			Cell sNameName = suiteName.createCell(0);
			Cell sNameValue = suiteName.createCell(1);
			sNameName.setCellValue("Suite");
			sNameName.setCellStyle(headerStyle);
			sNameValue.setCellValue(suite.getName());
			sNameValue.setCellStyle(headerStyle);
			row++;
			
			Row suiteDescription = suiteSheet.createRow((short) row);
			Cell sDescriptionName = suiteDescription.createCell(0);
			Cell sDescriptionValue = suiteDescription.createCell(1);
			sDescriptionName.setCellValue("Description");
			sDescriptionName.setCellStyle(headerStyle);
			sDescriptionValue.setCellValue(suite.getDescription());
			sDescriptionValue.setCellStyle(headerStyle);
			row++;
			
			Row suitePriority = suiteSheet.createRow((short) row);
			Cell sPriorityName = suitePriority.createCell(0);
			Cell sPriorityValue = suitePriority.createCell(1);
			sPriorityName.setCellValue("Priority");
			sPriorityName.setCellStyle(headerStyle);
			sPriorityValue.setCellValue(suite.getPriority().toString());
			sPriorityValue.setCellStyle(headerStyle);
			row += 2;
			
			// TODO - Populate
			Row acceptanceCriteria = suiteSheet.createRow((short) row);
			Cell acHeader = acceptanceCriteria.createCell(0);
			acHeader.setCellValue("Acceptance Criteria");
			acHeader.setCellStyle(headerStyle);

			if (suite.getAcceptanceCriteria().length > 0)
				for (String ac : suite.getAcceptanceCriteria()) {
					Row next = null;
					if ((next = suiteSheet.getRow(row)) == null)
						next = suiteSheet.createRow((short) row);
					Cell featureCell = next.createCell(1);
					featureCell.setCellValue(ac);
					featureCell.setCellStyle(headerStyle);
					row++;
				}
			row += 2;
			
			Row testHeader = suiteSheet.createRow((short) row);
			Cell testName = testHeader.createCell(0);
			testName.setCellValue("Test Name");
			testName.setCellStyle(tableHeaderStyle);
			
			Cell testDescription = testHeader.createCell(1);
			testDescription.setCellValue("Description");
			testDescription.setCellStyle(tableHeaderStyle);
			
			Cell testPassed = testHeader.createCell(2);
			testPassed.setCellValue("Result");
			testPassed.setCellStyle(tableHeaderStyle);
			
			Cell testType = testHeader.createCell(3);
			testType.setCellValue("Type");
			testType.setCellStyle(tableHeaderStyle);
			
			Cell testPriority = testHeader.createCell(4);
			testPriority.setCellValue("Priority");
			testPriority.setCellStyle(tableHeaderStyle);
			
			Cell testExpected = testHeader.createCell(5);
			testExpected.setCellValue("Expected Result");
			testExpected.setCellStyle(tableHeaderStyle);
			row++;
			
			for (TestCase testCase : suite.getTestCases()) {
				Row testRow = suiteSheet.createRow((short) row);
				Cell testNameTemp = testRow.createCell(0);
				testNameTemp.setCellValue(testCase.getTestName());
				
				Cell testDescriptionTemp = testRow.createCell(1);
				testDescriptionTemp.setCellValue(testCase.getTestDescription());
				
				Cell testPassedTemp = testRow.createCell(2);
				testPassedTemp.setCellValue(testCase.isResult());
				
				Cell testTypeTemp = testRow.createCell(3);
				testTypeTemp.setCellValue(testCase.getType().toString());
				
				Cell testPriorityTemp = testRow.createCell(4);
				testPriorityTemp.setCellValue(testCase.getPriority().toString());
				
				Cell testExpectedTemp = testRow.createCell(5);
				testExpectedTemp.setCellValue(testCase.getExpectedResult());
				row++;
			}
			
			// Resize
			suiteSheet.autoSizeColumn(0);
			suiteSheet.autoSizeColumn(1);
			suiteSheet.autoSizeColumn(2);
			suiteSheet.autoSizeColumn(3);
			suiteSheet.autoSizeColumn(4);
			suiteSheet.autoSizeColumn(5);
		}
	}

	private static void generateExcelHeader(Workbook report) {
		Sheet summary = report.createSheet("Summary");
		CreationHelper ch = report.getCreationHelper();
		int row = 1;

		Row application = summary.createRow((short) row);
		Cell applicationHeader = application.createCell(0);
		Cell applicationValue = application.createCell(1);
		applicationHeader.setCellValue("Name");
		applicationHeader.setCellStyle(headerStyle);
		applicationValue.setCellValue(ReportGenerator.getSetup().application());
		applicationValue.setCellStyle(headerStyle);
		row++;

		Row version = summary.createRow((short) row);
		Cell versionHeader = version.createCell(0);
		Cell versionValue = version.createCell(1);
		versionHeader.setCellValue(ch.createRichTextString("Version"));
		versionHeader.setCellStyle(headerStyle);
		versionValue.setCellValue(ReportGenerator.getSetup().version());
		versionValue.setCellStyle(headerStyle);
		row++;

		Row attempt = summary.createRow((short) row);
		Cell attemptHeader = attempt.createCell(0);
		Cell attemptValue = attempt.createCell(1);
		attemptHeader.setCellValue("Attempt");
		attemptHeader.setCellStyle(headerStyle);
		attemptValue.setCellValue(ReportGenerator.getSetup().attempt());
		attemptValue.setCellStyle(headerStyle);
		row += 2;

		Row features = summary.createRow((short) row);
		Cell featureHeader = features.createCell(0);
		featureHeader.setCellValue("Features");
		featureHeader.setCellStyle(headerStyle);

		if (ReportGenerator.getSetup().features().length > 0)
			for (String feature : ReportGenerator.getSetup().features()) {
				Row next = null;
				if ((next = summary.getRow(row)) == null)
					next = summary.createRow((short) row);
				Cell featureCell = next.createCell(1);
				featureCell.setCellValue(feature);
				featureCell.setCellStyle(headerStyle);
				row++;
			}
		row++;

		Row defects = summary.createRow((short) row);
		Cell defectHeader = defects.createCell(0);
		defectHeader.setCellValue("Defects");
		defectHeader.setCellStyle(headerStyle);

		if (ReportGenerator.getSetup().knownDefects().length > 0)
			for (String defect : ReportGenerator.getSetup().knownDefects()) {
				Row next = null;
				if ((next = summary.getRow(row)) == null)
					next = summary.createRow((short) row);
				Cell defectCell = next.createCell(1);
				defectCell.setCellValue(defect);
				defectCell.setCellStyle(headerStyle);
				row++;
			}
		row++;

		int[] results = TestHandler.getEntireSummary();
		// TODO - Formula for percentages
		
		Row passedSummary = summary.createRow((short) row);
		Cell passedTitle = passedSummary.createCell(0);
		passedTitle.setCellStyle(headerStyle);
		passedTitle.setCellValue("Passed Tests");
		
		Cell passedNumber = passedSummary.createCell(1);
		passedNumber.setCellStyle(headerStyle);
		passedNumber.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		passedNumber.setCellFormula("SUM(C17:C100)");
		
		Cell passedPercentage = passedSummary.createCell(2);
		passedPercentage.setCellStyle(headerStyle);
		if (results[0] != 0 && results[1] != 0)
			passedPercentage.setCellValue((int) (results[1] * 100.0f) / results[0] + "%");
		else
			passedPercentage.setCellValue(0 + "%");
		row++;
		
		Row failedSummary = summary.createRow((short) row);
		Cell failedTitle = failedSummary.createCell(0);
		failedTitle.setCellStyle(headerStyle);
		failedTitle.setCellValue("Failed Tests");
		
		Cell failedNumber = failedSummary.createCell(1);
		failedNumber.setCellStyle(headerStyle);
		failedNumber.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		failedNumber.setCellFormula("SUM(D17:D100)");
		
		Cell failedPercentage = failedSummary.createCell(2);
		failedPercentage.setCellStyle(headerStyle);
		if (results[0] != 0 && results[2] != 0)
			failedPercentage.setCellValue((int) (results[2] * 100.0f) / results[0] + "%");
		else
			failedPercentage.setCellValue(0 + "%");
		row++;
		
		Row naSummary = summary.createRow((short) row);
		Cell naTitle = naSummary.createCell(0);
		naTitle.setCellStyle(headerStyle);
		naTitle.setCellValue("Tests Not Run");
		
		Cell naNumber = naSummary.createCell(1);
		naNumber.setCellStyle(headerStyle);
		naNumber.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		naNumber.setCellFormula("SUM(E17:E100)");
		
		Cell naPercentage = naSummary.createCell(2);
		naPercentage.setCellStyle(headerStyle);
		if (results[0] != 0 && results[3] != 0)
			naPercentage.setCellValue((int) (results[3] * 100.0f) / results[0] + "%");
		else
			naPercentage.setCellValue(0 + "%");
		row++;
		
		Row totalSummary = summary.createRow((short) row);
		Cell totalTitle = totalSummary.createCell(0);
		totalTitle.setCellStyle(headerStyle);
		totalTitle.setCellValue("Total");
		
		Cell totalNumber = totalSummary.createCell(1);
		totalNumber.setCellStyle(headerStyle);
		totalNumber.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalNumber.setCellFormula("SUM(F17:F100)");
		
		Cell totalPercentage = totalSummary.createCell(2);
		totalPercentage.setCellStyle(headerStyle);
		totalPercentage.setCellValue(100 + "%");
		row += 2;
		
		/*
		 * Suite Table 
		 */
		
		Row tableSummary = summary.createRow((short) row);
		Cell suiteName = tableSummary.createCell(0);
		suiteName.setCellValue("Suite ID");
		suiteName.setCellStyle(tableHeaderStyle);
		
		Cell suiteDescription = tableSummary.createCell(1);
		suiteDescription.setCellValue("Description");
		suiteDescription.setCellStyle(tableHeaderStyle);
		
		Cell passed = tableSummary.createCell(2);
		passed.setCellValue("Passed");
		passed.setCellStyle(tableHeaderStyle);
		
		Cell failed = tableSummary.createCell(3);
		failed.setCellValue("Failed");
		failed.setCellStyle(tableHeaderStyle);
		
		Cell notRun = tableSummary.createCell(4);
		notRun.setCellValue("Not Tested");
		notRun.setCellStyle(tableHeaderStyle);
		
		Cell total = tableSummary.createCell(5);
		total.setCellValue("Total");
		total.setCellStyle(tableHeaderStyle);
		row++;
		
		Iterator<?> iterator = ReportGenerator.SUITES.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<String, TestSuite> pair = (Entry<String, TestSuite>) iterator.next();
			TestSuite suite = pair.getValue();
			
			int totalInt = 0;
			int passedInt = 0;
			int failedInt = 0;
			int naInt = 0;
			
			for (TestCase testCase : suite.getTestCases()) {
				totalInt++;
				if (testCase.isResult().equals("Passed"))
					passedInt++;
				else if (testCase.isResult().equals("Failed"))
					failedInt++;
				else
					naInt++;
			}
			
			Row suiteRow = summary.createRow((short) row);
			Cell suideId = suiteRow.createCell(0);
			suideId.setCellValue(suite.getName());
			
			Cell suideDes = suiteRow.createCell(1);
			suideDes.setCellValue(suite.getDescription());
			
			Cell suidePassed = suiteRow.createCell(2);
			suidePassed.setCellValue(passedInt);
			
			Cell suideFailed = suiteRow.createCell(3);
			suideFailed.setCellValue(failedInt);
			
			Cell suideNa = suiteRow.createCell(4);
			suideNa.setCellValue(naInt);
			
			Cell suideTotal = suiteRow.createCell(5);
			suideTotal.setCellValue(totalInt);
			
			row++;
		}
		
		// Resize
		summary.autoSizeColumn(0);
		summary.autoSizeColumn(1);
		summary.autoSizeColumn(2);
		summary.autoSizeColumn(3);
		summary.autoSizeColumn(4);
		summary.autoSizeColumn(5);
	}
}

package com.rysource.report;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * INTERNAL USE ONLY.
 * Contains common methods and functions used in generating reports from test data.
 * @author ryandixon1993@gmail.com
 */
public class TestHandler {

	protected static int[] getEntireSummary() {
		Iterator<?> iterator = ReportGenerator.SUITES.entrySet().iterator();
		int total = 0;
		int passed = 0;
		int failed = 0;
		int na = 0;
		
		while (iterator.hasNext()) {
			
			@SuppressWarnings("unchecked")
			Map.Entry<String, TestSuite> pair = (Entry<String, TestSuite>) iterator.next();
			TestSuite suite = pair.getValue();
			for (TestCase testCase : suite.getTestCases()) {
				total++;
				if (testCase.isResult().equals("Passed"))
					passed++;
				else if (testCase.isResult().equals("Failed"))
					failed++;
				else
					na++;
			}
		}
		return new int[] {total, passed, failed, na};
	}
	
}

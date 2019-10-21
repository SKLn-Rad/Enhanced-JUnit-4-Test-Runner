package com.rysource.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Set;

public class ExtentReport {
    static ExtentHtmlReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest test;

    public static void generateReport(String filePath) {
        htmlReporter = new ExtentHtmlReporter(filePath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        Set<String> suiteKeys = ReportGenerator.SUITES.keySet();
        for (String key : suiteKeys) {
            TestSuite suite = ReportGenerator.SUITES.get(key);

            for (TestCase testCase : suite.getTestCases()) {
                boolean isIgnored = testCase.getResult() == "Not Executed";
                boolean isPassed = testCase.getResult() == "Passed";
                test = extent.createTest(testCase.getTestName(), testCase.getTestDescription());

                if (isIgnored) {
                    test.log(Status.SKIP, testCase.getExpectedResult());
                } else if (isPassed) {
                    test.log(Status.PASS, testCase.getExpectedResult());
                } else {
                    test.log(Status.FAIL, testCase.getExpectedResult() + "\n" + testCase.getStack());
                }
            }
        }

        extent.flush();
    }
}

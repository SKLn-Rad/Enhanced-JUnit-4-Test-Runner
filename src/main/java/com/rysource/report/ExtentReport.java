package com.rysource.report;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.rysource.runner.EnhancedAssertion;
import com.rysource.runner.EnhancedLogging;
import org.junit.runners.Suite;

import java.lang.reflect.Array;
import java.util.*;

public class ExtentReport {
    static ExtentHtmlReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest test;


    /**
     * Name:generateReport
     * Takes the outcomes from Test Suites, Cases and Enhanced Assertions and writes the data to the @ReportGenerator to be formatted
     *
     * @param filePath String : Accepts filepath in string format
     */
    public static void generateReport(String filePath) {


        htmlReporter = new ExtentHtmlReporter(filePath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);

        htmlReporter.config().setDocumentTitle("Automated Testing report");
        htmlReporter.config().setReportName(filePath.substring(0, filePath.length()-5));
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().enableTimeline(false);


        ExtentTest reportsuite;
        ExtentTest test;

        Set<String> suiteKeys = ReportGenerator.SUITES.keySet();

        for (String key : suiteKeys) {

            TestSuite suite = ReportGenerator.SUITES.get(key);
            String sFormattedPriority = setPriorityTextformat(suite.getPriority().toString());
            reportsuite = extent.createTest("<p>" + suite.getName() + "<br /><font size=2>" + suite.getDescription().replace("\n", "<br />") + "</font><br />" + sFormattedPriority + "</p>");
            reportsuite.getModel().setStartTime(null);

            for (TestCase testCase : suite.getTestCases()) {
                boolean isIgnored = testCase.getResult() == "Not Executed";
                boolean isPassed = testCase.getResult() == "Passed";

                sFormattedPriority = setPriorityTextformat(testCase.getPriority().toString());

                test = reportsuite.createNode(testCase.getTestName(), testCase.getExpectedResult().replace("\n", "<br />") + "<br/>" + sFormattedPriority + "<br/>");
                if (isIgnored) {

                    reportsuite.log(Status.SKIP, MarkupHelper.createLabel("Test ignored in run", ExtentColor.GREY));
                } else {
                    if (reportsuite.getModel().getStartTime() == null) {
                        reportsuite.getModel().setStartTime(new Date(testCase.getTestStarted()));
                    }

                    ArrayList<EnhancedAssertion> assertions = testCase.getEnhancedAssertions();

                    boolean isHardFailure = assertions.stream().anyMatch(a -> {
                        boolean isHard = a.isHard();
                        boolean hasFailed = !a.isResult();
                        return isHard && hasFailed;
                    });


                    for (EnhancedAssertion sa : testCase.getEnhancedAssertions()) {
                        boolean isHard = sa.isHard();
                        boolean hasFailed = !sa.isResult();
                        boolean isInfo = sa.isInfo();
                        test.getModel().setStartTime(sa.getRunTime());
                        test.getModel().setEndTime(sa.getRunTime());

                        String sTestInfo = sa.getMessage().replace("\n", "<br />");
                        //test.getModel().getLogContext().getLast().setTimestamp(sa.getRunTime());
                        if (hasFailed && isHard) {
                            //test.getModel().getLogContext().getLast().setTimestamp(sa.getRunTime());
                            test.log(Status.FAIL, MarkupHelper.createLabel(sTestInfo, ExtentColor.RED));
                            EnhancedLogging.debug(sTestInfo + "FAILED");
                        } else if (hasFailed) {
                            test.log(Status.ERROR, MarkupHelper.createLabel(sTestInfo, ExtentColor.ORANGE));
                            EnhancedLogging.debug(sTestInfo + "ERROR Found");
                        } else if (isInfo) {
                            test.log(Status.INFO, MarkupHelper.createLabel(sTestInfo, ExtentColor.BLUE));
                            EnhancedLogging.debug(sTestInfo + "INFO");
                        } else {
                            test.log(Status.PASS, MarkupHelper.createLabel(sTestInfo, ExtentColor.GREEN));
                            EnhancedLogging.debug(sTestInfo + "PASSED");

                        }
                        test.getModel().setStartTime((new Date(testCase.getTestStarted())));
                        test.getModel().setEndTime((new Date(testCase.getTestFinished())));
                        reportsuite.getModel().setEndTime(new Date(testCase.getTestFinished()));

                    }


                }
            }

        }


        extent.flush();
    }


    /**
     * Name:  setPriorityTextformat
     * This is used to ensure the relevant priority levels are displayed in the report in the relvant colour codes used through our test process
     *
     * @param sPriority String: Accepts Low, High, medium, Critical - Not Case sensitive, Defaults to Black Text
     * @return String : the entered sPriority string is returned in a new string wrapped in HTML Markup for appropriate color and bold
     */
    private static String setPriorityTextformat(String sPriority) {

        switch (sPriority.toLowerCase()) {

            case "low":
                return "<b><font size=2 color=\"31A11F\"> Priority: " + sPriority + "</font></b>";

            case "medium":
                return "<b><font size=2 color=\"F2820C\">Priority: " + sPriority + " </font></b>";

            case "high":
                return "<b><font size=2 color=\"861E08\">Priority: " + sPriority + " </font></b>";

            case "critical":
                return "<b><font size=2 color=\"FC2F03\">Priority: " + sPriority + " </font></b>";

            default:
                return "<b><font size=2 color=\"black\">Priority: " + sPriority + " </font></b>";

        }
    }
}

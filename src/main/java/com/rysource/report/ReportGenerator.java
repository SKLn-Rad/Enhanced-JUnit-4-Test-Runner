package com.rysource.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.rysource.annotations.Setup;
import com.rysource.annotations.Setup.ReportType;
import com.rysource.runner.EnhancedLogging;


/**
 * INTERNAL USE ONLY.
 * This class is the thread spawned on death of the test harness and is used to generate reports to the user.
 *
 * @author ryandixon1993@gmail.com
 */
public class ReportGenerator extends Thread {

    private static Setup setup;

    private static String path = "";
    private static File outputFile;
    private static boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
            getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;

    public static final HashMap<String, TestSuite> SUITES = new HashMap<String, TestSuite>();

    public ReportGenerator(Setup setup) {
        ReportGenerator.setup = setup;
    }

    @Override
    public synchronized void start() {
        // Detect Settings for Report Type
        System.out.println("Finished testing. getting reportable data from annotations...");
        if (SUITES == null) {
            System.out.println("Something went wrong, could not generate report.");
            return;
        } else {

        }

        System.out.println();
        System.out.println("Preparing report...");
        prepareReport();
        super.start();
    }





    private static void prepareFilePath(String reportName) {
        EnhancedLogging.debug("Preparing new report");
        if (new File(path + reportName).exists()) {
            System.out.println("Found old test, deleting.");
            new File(path).delete();
        }
        System.out.println("Outputting report to " + new File(path).getAbsolutePath() + File.separator + reportName);
        outputFile = new File(path + reportName);

        try {
            outputFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void prepareReport() {
        LocalDate localDate = LocalDate.now();
        String sFileName = "";
        for (ReportType report : setup.reportType()) {
            switch (report) {
                case EXCEL:
                    if (isDebug) {
                        sFileName = getSetup().application() + " report.xlsx";
                    } else {
                        LocalDate.now();
                        sFileName = getSetup().application() + " report - " + localDate + ".xlsx";
                    }
                    prepareFilePath(sFileName);
                    ExcelReport.generateExcelReport(outputFile);
                    break;
                case EXTENT_REPORT:

                    if (isDebug) {

                        sFileName =  getSetup().application() + " report.html";
                    } else {
                        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mmZ");
                        LocalDate.now();
                        sFileName =  getSetup().application() + " report - " + localDate + ".html";
                    }

                    prepareFilePath(sFileName);
                    ExtentReport.generateReport(path + sFileName);

//                    File upFile = new File (path + getSetup().application() + " report - " + localDate + ".html");
//                    try{
//                        uploadtoConfluence(upFile);
//                    }catch(Exception e){
//                        EnhancedLogging.debug("Confluence Upload Errors: \n" + e.getMessage());
//                    }

                    break;
                case JUNIT_XML:
                default:
                    prepareFilePath("report.xml");
                    JUnitReport.generateJUnitReport(outputFile);
                    break;
            }
        }
    }

    public static Setup getSetup() {
        return ReportGenerator.setup;
    }

}

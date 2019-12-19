package com.rysource.report;

import com.sun.xml.internal.txw2.output.XmlSerializer;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class JUnitReport {

	private static Writer writer;
	private static XmlSerializer testSuiteSerializer;

	public static void generateJUnitReport(File file) {
		try {
			startJUnitOutput(writer);
			recordTests();
			endTestSuites();
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IllegalStateException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	private static void recordTests() throws IllegalArgumentException, IllegalStateException, IOException {
		System.out.println("Enter Record");
		Iterator<?> iterator = ReportGenerator.SUITES.entrySet().iterator();
		while (iterator.hasNext()) {
			TestSuite suite = (TestSuite) iterator.next();
			testSuiteSerializer.startTag(null, "testsuite");
			testSuiteSerializer.attribute(null, "name", suite.getName());
			System.out.println("Parsing: " + suite.getName());
			
			for (TestCase testCase : suite.getTestCases()) {
				System.out.println("Parsing: " + testCase.getTestName());
				testSuiteSerializer.startTag(null, "testcase");
				testSuiteSerializer.attribute(null, "classname", testCase.getClassName());
				testSuiteSerializer.attribute(null, "name", testCase.getTestName());

				if (testCase.getResult().equals("Failed")) {
					testSuiteSerializer.startTag(null, "failure");
					if (testCase.getStack() != null) {
						String reason = testCase.getStack().substring(0, testCase.getStack().indexOf('\n'));
						String message = "";
						int index = reason.indexOf(':');
						if (index > -1) {
							message = reason.substring(index + 1);
							reason = reason.substring(0, index);
						}
						testSuiteSerializer.attribute(null, "message", message);
						testSuiteSerializer.attribute(null, "type", reason);
						testSuiteSerializer.text(testCase.getStack());
					}
					testSuiteSerializer.endTag(null, "failure");
				}

				testSuiteSerializer.attribute(null, "time",
						String.format("%.3f", (testCase.getTestFinished() - testCase.getTestStarted())));

				testSuiteSerializer.endTag(null, "testcase");
			}

			testSuiteSerializer.endTag(null, "testsuite");
		}
	}

	private static void startJUnitOutput(Writer writer)
			throws IllegalArgumentException, IllegalStateException, IOException, XmlPullParserException {
		System.out.println("Starting JUnit Parser");
		JUnitReport.writer = writer;
		testSuiteSerializer = newSerializer(writer);
		testSuiteSerializer.startDocument(null, null);
		testSuiteSerializer.startTag(null, "testsuites");
	}

	private static XmlSerializer newSerializer(Writer writer)
			throws XmlPullParserException, IllegalArgumentException, IllegalStateException, IOException {
		XmlPullParserFactory pf = XmlPullParserFactory.newInstance();
		XmlSerializer serializer = pf.newSerializer();
		serializer.setOutput(writer);
		return serializer;
	}

	private static void endTestSuites() throws IllegalArgumentException, IllegalStateException, IOException {
		System.out.println("Ending JUnit Parser");
		testSuiteSerializer.endTag(null, "testsuites");
		testSuiteSerializer.endDocument();
		testSuiteSerializer.flush();
		writer.flush();
		writer.close();
		System.out.println("Finished writing JUnit XML report.");
	}
}

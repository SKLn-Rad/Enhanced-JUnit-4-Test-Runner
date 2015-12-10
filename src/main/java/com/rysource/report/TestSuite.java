package com.rysource.report;

import java.util.ArrayList;

import com.rysource.annotations.SuiteInformation.SuitePriority;

/**
 * INTERNAL USE ONLY.
 * POJO for containing a a suite of tests.
 * @author ryandixon1993@gmail.com
 */
public class TestSuite {

	private String name;
	private String description;
	private SuitePriority priority;
	private String[] acceptanceCriteria;
	
	private ArrayList<TestCase> testCases;
	
	public TestSuite(String name, String description, SuitePriority priority, String[] ac) {
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.acceptanceCriteria = ac;
		testCases = new ArrayList<TestCase>();
	}

	public void addTestCase(TestCase testCase) {
		testCases.add(testCase);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SuitePriority getPriority() {
		return priority;
	}

	public void setPriority(SuitePriority priority) {
		this.priority = priority;
	}

	public String[] getAcceptanceCriteria() {
		return acceptanceCriteria;
	}

	public void setAcceptanceCriteria(String[] acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}

	public ArrayList<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(ArrayList<TestCase> testCases) {
		this.testCases = testCases;
	}
	
}

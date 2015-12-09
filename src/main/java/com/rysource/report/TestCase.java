package com.rysource.report;

import com.rysource.runner.annotations.TestInformation.TestPriority;
import com.rysource.runner.annotations.TestInformation.TestType;

/**
 * INTERNAL USE ONLY. POJO for containing a test case.
 * 
 * @author ryandixon1993@gmail.com
 */
public class TestCase {

	private String className;
	private String testName;
	private String testDescription;
	private String expectedResult;
	private String result;
	private TestType type;
	private TestPriority priority;
	private long testStarted;
	private long testFinished;

	/*
	 * On Failure
	 */
	private String message;
	private String stack;

	public TestCase(String className, String testName, String testDescription, String expectedResult, TestType type,
			TestPriority priority, String result, long testStarted, String message, String stack) {
		this.className = className;
		this.testName = testName;
		this.testDescription = testDescription;
		this.expectedResult = expectedResult;
		this.type = type;
		this.priority = priority;
		this.result = result;
		this.testStarted = testStarted;
		this.testFinished = System.currentTimeMillis();
		this.message = message;
		this.stack = stack;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public TestType getType() {
		return type;
	}

	public void setType(TestType type) {
		this.type = type;
	}

	public TestPriority getPriority() {
		return priority;
	}

	public void setPriority(TestPriority priority) {
		this.priority = priority;
	}

	public String isResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getTestStarted() {
		return testStarted;
	}

	public void setTestStarted(long testStarted) {
		this.testStarted = testStarted;
	}

	public String getResult() {
		return result;
	}

	public long getTestFinished() {
		return testFinished;
	}

	public void setTestFinished(long testFinished) {
		this.testFinished = testFinished;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

}

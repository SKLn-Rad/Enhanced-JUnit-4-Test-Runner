package com.rysource.report;

import com.rysource.annotations.TestInformation.TestPriority;
import com.rysource.annotations.TestInformation.TestType;
import com.rysource.runner.EnhancedAssertion;

import java.util.ArrayList;

/**
 * INTERNAL USE ONLY. POJO for containing a test case.
 *
 * @author ryandixon1993@gmail.com
 */
public class TestCase {

    private String className;
    private String name;
    private String description;
    private String expectedBehaviour;
    private String result;
    private TestType type;
    private TestPriority priority;
    private long started;
    private long finished;

    /*
     * On Failure
     */
    private String message;
    private String stack;
    private ArrayList<EnhancedAssertion> enhancedAssertions;
    private int runOrder;

    /**
     * NAME:  TestCase
     * Defines and populates the Test case Object
     *
     * @param className :  String
     * @param testName: String
     * @param testDescription: String
     * @param expectedResult: String
     * @param type: TestType
     * @param priority: TestPriority
     * @param result: String
     * @param testStarted: Long (Date)
     * @param testFinished: long (Date)
     * @param message: String
     * @param stack: String
     * @param enhancedAssertions: ArrayList<EnhancedAssertion>
     * @param iTestRunOrder: int
     */

    public TestCase(String className, String testName, String testDescription, String expectedResult, TestType type,
                    TestPriority priority, String result, long testStarted, long testFinished, String message, String stack, ArrayList<EnhancedAssertion> enhancedAssertions, int iTestRunOrder) {
        this.className = className;
        this.name = testName;
        this.description = testDescription;
        this.expectedBehaviour = expectedResult;
        this.type = type;
        this.priority = priority;
        this.result = result;
        this.started = testStarted;
        this.finished = System.currentTimeMillis();;
        this.message = message;
        this.stack = stack;
        this.enhancedAssertions = enhancedAssertions;
        this.runOrder = iTestRunOrder;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTestName() {
        return name;
    }

    public void setTestName(String testName) {
        this.name = testName;
    }

    public String getTestDescription() {
        return description;
    }

    public void setTestDescription(String testDescription) {
        this.description = testDescription;
    }

    public String getExpectedResult() {
        return expectedBehaviour;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedBehaviour = expectedResult;
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
        return started;
    }

    public void setTestStarted(long testStarted) {
        this.started = testStarted;
    }

    public String getResult() {
        return result;
    }

    public long getTestFinished() {
        return finished;
    }

    public void setTestFinished(long testFinished) {
        this.finished = testFinished;
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

    public ArrayList<EnhancedAssertion> getEnhancedAssertions() {
        return enhancedAssertions;
    }

    public void setEnhancedAssertions(ArrayList<EnhancedAssertion> enhancedAssertions) {
        this.enhancedAssertions = enhancedAssertions;
    }
}

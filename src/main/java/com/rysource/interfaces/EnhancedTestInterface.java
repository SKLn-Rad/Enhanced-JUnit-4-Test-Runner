package com.rysource.interfaces;

import org.junit.Ignore;

import java.io.IOException;

/**
 * This class should be implemented in the same place as the RunWith annotation to get access to live notifications on test results.
 */
/**
 * @author ryandixon1993@gmail.com
 */
public interface EnhancedTestInterface {

	/**
	 * Fired upon the execution of a new test
	 * 
	 * @param result
	 *            the result of the test
	 * @param className
	 *            The class name of the test method being executed
	 * @param methodName
	 *            The method name of the test being executed
	 */
	public void onTestFinished(boolean result, String className, String methodName);

	/**
	 * Fired when a test starts
	 * 
	 * @param className
	 *            The class name of the test
	 * @param methodName
	 *            The method name of the test
	 */
	public void onTestStarted(String className, String methodName) throws IOException;

	/**
	 * Fired upon a test failure
	 * 
	 * @param className
	 *            The class in which the failure was fired
	 * @param methodName
	 *            The method in which the failure was fired
	 * @param message
	 *            The message attached to the assertion
	 * @param stack
	 *            The stack trace of the failure
	 */
	public void onTestFailure(String className, String methodName, String message, String stack);

	/**
	 * Fired upon a test passing
	 * 
	 * @param className
	 *            The class name in which the test passed
	 * @param methodName
	 *            The method name of the test which passed
	 */
	public void onTestPassed(String className, String methodName);

	/**
	 * Fired when a test is found to have the {@link Ignore} annotation
	 * 
	 * @param className
	 *            The class name of the test
	 * @param methodName
	 *            The method name of the test
	 */
	public void onTestIgnored(String className, String methodName);
}

package com.rysource.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link TestInformation} annotation is used at method level to describe
 * the tests being executed.
 * 
 * All methods using this annotation should have a parent class using the
 * {@link SuiteInformation} annotation.
 * 
 * @author ryandixon1993@gmail.com
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestInformation {

	/**
	 * This enumeration represents the priority of the test being executed with
	 * NONE being the lowest, and CRITICAL being the highest.
	 * 
	 * @author ryandixon1993@gmail.com
	 *
	 */
	enum TestPriority {
		NONE, LOW, MEDIUM, HIGH, CRITICAL
	}

	/**
	 * This enumeration represents the type of test being executed. MANUAL tests
	 * are sent to the report for the user to manually perform after the test
	 * cycle.
	 * 
	 * @author ryandixon1993@gmail.com
	 *
	 */
	enum TestType {
		MANUAL, AUTOMATIC
	}

	/**
	 * The priority of the test being executed. This is sent to the report and
	 * used to prioritise defects better in a more AGILE manner. By default this
	 * is MEDIUM.
	 * 
	 * @return - The priority of the test.
	 */
	TestPriority priority() default TestPriority.MEDIUM;

	/**
	 * The type of test being executed. If MANUAL, This is sent to the report
	 * for the user to fill in later. By default this is set to AUTOMATIC.
	 * 
	 * @return - The type of test being executed
	 */
	TestType type() default TestType.AUTOMATIC;

	/**
	 * The name of the test to be represented as such on the report. If the
	 * reporter find the default of 'Unknown test case', then the method name is
	 * used instead.
	 * 
	 * @return - The name of the test being executed.
	 */
	String testName() default "Unknown test case";


	/**
	 * The order that the test should be run in. If the the default is found then it reverts to Standard run order
	 */
	int testRunOrder() default Integer.MAX_VALUE;

	/**
	 * A description of the test to be represented as such on the report. By
	 * default this is 'No description given'
	 * 
	 * @return - The description of the test being executed.
	 */
	String testDescription() default "No description given";

	/**
	 * The expected behaviour of test. This is sent to the reported in the event
	 * the test is using ATDD or BDD style. By default this is 'No expected
	 * behaviour supplied'.
	 * 
	 * @return - The expected behaviour of the test case.
	 */
	String expectedBehaviour() default "No expected behaviour supplied";

}

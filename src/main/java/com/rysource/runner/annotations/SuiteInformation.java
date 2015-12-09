package com.rysource.runner.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link SuiteInformation} annotation is used to pass meta data regarding
 * the test suite to the runner. All test classes should be annotated with this
 * in order to identify where to look for {@link TestInformation} annotations.
 * 
 * @author ryandixon1993@gmail.com
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SuiteInformation {

	/**
	 * An enumeration used in reports to represent the priority of the suite
	 * being executed. This helps to prioritise defects in your Sprint backlogs.
	 * 
	 * @author ryandixon1993@gmail.com
	 *
	 */
	public enum SuitePriority {
		NONE, LOW, MEDIUM, HIGH, CRITICAL
	}

	/**
	 * The priority of the current test suite being executed. By default this is
	 * set to SuitePriority.MEDIUM
	 * 
	 * @return - The priority of the test suite.
	 */
	SuitePriority priority() default SuitePriority.MEDIUM;

	/**
	 * The name of the test suite. This is used to provide better reports and
	 * separate test cases into grouped sections. Please note you can have more
	 * than one class with this annotation matching.
	 * 
	 * @return - The name of the test suite.
	 */
	String suiteName() default "Unknown test suite";

	/**
	 * The description of the test suite. This is used to provided information
	 * as to what the test suite is supposed to cover.
	 * 
	 * @return - The description of the test suite.
	 */
	String suiteDescription() default "No description given";

	/**
	 * The AGILE acceptance criteria of the test suite. This often reflects the
	 * Epics and Stories in which these tests are created under. Note: This is
	 * optional and will be ignored in reports if not provided.
	 * 
	 * @return - A String[] of acceptance criteria for the test suite.
	 */
	String[] suiteAcceptanceCriteria() default {};
}

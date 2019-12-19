package com.rysource.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link Setup} annotation provides basic meta data regarding the AUT
 * (Application under test) which is included into the reports.
 * 
 * @author ryandixon1993@gmail.com
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Setup {

	/**
	 * A enumeration used to identify the style of report to output at the end
	 * of a test cycle. By default this is a PDF report.
	 * 
	 * @author ryandixon1993@gmail.com
	 *
	 */
	enum ReportType {
		EXCEL, JUNIT_XML, EXTENT_REPORT,
	}

	/**
	 * An array of {@link ReportType} used to generate a report at the end of the test cycle.
	 * 
	 * @return - The format of report to output
	 */
	ReportType[] reportType() default {};

	/**
	 * The API Key when set, allows enterprise level features such as data
	 * retention, regression reports, stability reports and much more. See the
	 * website for details on the benefits.
	 * 
	 * @return - The API Key of the enterprise user as supplied after purchase
	 */
	String apiKey() default "";

	/**
	 * The name of the AUT (Application under test). By default it is 'Unknown
	 * Application'.
	 * 
	 * @return - The new name of the AUT
	 */
	String application() default "Unknown Application";

	/**
	 * The version of the AUT (Application under test). By default it is
	 * 'Unknown Version'.
	 * 
	 * @return - The new version of the AUT
	 */

	String version() default "Unknown Version";

	/**
	 * A String[] of features. This may be new features for this release or a
	 * collation of all features the AUT supports. This is added to the report
	 * to help with conforming to AGILE practices.
	 * 
	 * @return A String[] of features
	 */
	String[] features() default {};

	/**
	 * A String[] of known defects. This may be regression defects or a
	 * collation of all known defects on the AUT. This is added to the report to
	 * help with conforming to AGILE practices.
	 * 
	 * @return A String[] of defects
	 */
	String[] knownDefects() default {};

	/**
	 * The release attempt, for example if the AUT recently failed a regression
	 * test prior to release then this may be set to 2. This is added to the
	 * report to help with conforming to AGILE practices.
	 * 
	 * @return An Integer representing the release attempt
	 */
	int attempt() default 1;

	/**
	 * Set a boolean flag to allow tests with the {@link Ignore} annotation to
	 * be included as part of the report. For example, include manual tests for
	 * you to fill in post-automation.
	 * 
	 * @return A boolean representing whether the report should reflect tests
	 *         with the {@link Ignore} annotation
	 */
	boolean retainIgnoredTests() default true;

	/**
	 * Set a custom location for the Test Report to be sent to. This location
	 * must be an absolute path or can be left blank to be put in the project
	 * workspace.
	 * 
	 * @return A String absolute location of where the Report will appear post test
	 */
	String reportLocation() default "";
}

package com.rysource.runner;

import java.util.LinkedList;
import java.util.List;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.rysource.annotations.Setup;
import com.rysource.annotations.SuiteInformation;
import com.rysource.annotations.SuiteInformation.SuitePriority;
import com.rysource.defaults.DefaultSetup;
import com.rysource.interfaces.EnhancedTestInterface;
import com.rysource.report.ReportGenerator;
import com.rysource.report.TestSuite;

/**
 * A custom version of the JUnit Suite package. This must be called with RunWith to get the functionality out of this solution.
 * @author ryandixon1993@gmail.com
 */
public class EnhancedSuite extends Suite {

	private static EnhancedTestInterface eti;

	private static final String HEADER = "--- EJ4TR - Version 2.0.0 ---";
	
	private static boolean setupFound = false;
	private static boolean interfaceFound = false;

	public EnhancedSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
		super(null, getRunners(getAnnotatedClasses(klass)));
	}

	private static Class<?>[] getAnnotatedClasses(Class<?> klass) throws InitializationError {
		System.out.println(HEADER);
		System.out.println("Performing initial configuration check.");

		Suite.SuiteClasses annotation = klass.getAnnotation(Suite.SuiteClasses.class);
		if (annotation == null) {
			throw new InitializationError(
					String.format("class '%s' must have a SuiteClasses annotation", klass.getName()));
		}

		// Test if class implements test interface
		// If so, bind to runner
		if (EnhancedTestInterface.class.isAssignableFrom(klass)) {
			System.out.println("Found test interface! Binding to runner.");
			interfaceFound = true;
			try {
				eti = (EnhancedTestInterface) klass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		// Test if class implements Setup annotation
		if (klass.isAnnotationPresent(Setup.class)) {
			System.out.println("Found setup annotation! Binding to reporter.");
			Runtime.getRuntime().addShutdownHook(new ReportGenerator(klass.getAnnotation(Setup.class)));
			setupFound = true;
		}

		System.out.println();
		return annotation.value();
	}

	public static List<Runner> getRunners(Class<?>[] classes) throws InitializationError {
		// If setup is not passed in, then set default
		if (!setupFound) {
			Runtime.getRuntime().addShutdownHook(new ReportGenerator(DefaultSetup.class.getAnnotation(Setup.class)));
			System.out.println("No setup annotation found. Using defaults.");
		}
		if (!interfaceFound) {
			System.out.println("No test interface found...");
		}

		List<Runner> runners = new LinkedList<Runner>();

		List<Runner> runnerOrder = new LinkedList<Runner>();


		System.out.println("Searching for suite classes...");
		for (Class<?> klazz : classes) {
			System.out.println("Found new test suite: " + klazz.getName());
			getSuiteInformation(klazz);
			EnhancedTestRunner etr = new EnhancedTestRunner(klazz);
			if (eti != null) {
				etr.addTestInterface(eti);
			}
			runners.add(etr);
		}

		System.out.println();
		System.out.println("Starting tests...");
		System.out.println();



		return runners;
	}

	// 1) Create helper to get all unique drivers in each method in klazz
	// 2) Create a new suite for EACH of the drivers in that list, changing its name based on the runner
	// 3) When running a test check how many times it has been run against the same list to figure out what driver it is, and then add to the correct suite for that driver
	// 4) ?????
	// 5) Profit?

	// Example of below
	// Map<TestName, int>
	// List<drivers>
	// On test run, Map.get(TestName), if null set 0 and get drivers.get(0) -> Example Browser Web
	// If above is 0 already, set to + 1 and do the same - drivers.get(1) -> Example: Android Web
	// On Test finished, Map.get(TestName) and get suite for int -> Example: Suite 0 -> Browser Web, Suite 1 -> Android Web
	private static void getSuiteInformation(Class<?> klazz) {
		if (ReportGenerator.SUITES == null) {
			System.out.println("An unknown error occured.");
		} else {
			if (klazz.isAnnotationPresent(SuiteInformation.class)) {
				SuiteInformation suite = klazz.getAnnotation(SuiteInformation.class);
				if (!ReportGenerator.SUITES.containsKey(suite.suiteName())) {
					System.out.println("Found suite information! Binding to reporter.");
					ReportGenerator.SUITES.put(suite.suiteName(), new TestSuite(suite.suiteName(), suite.suiteDescription(), suite.priority(), suite.suiteAcceptanceCriteria()));
				}
			} else {
				if (!ReportGenerator.SUITES.containsKey(klazz.getName())) {
					System.out.println("No SuiteInformation annotation found in " + klazz.getName()
							+ ", using class name instead with defaults.");
					ReportGenerator.SUITES.put(klazz.getName(),
							new TestSuite(klazz.getName(), "", SuitePriority.MEDIUM, new String[] {}));
				}
			}
		}
	}

}

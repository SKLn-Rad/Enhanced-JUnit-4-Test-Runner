import com.rysource.annotations.Setup;
import com.rysource.annotations.SuiteInformation;
import com.rysource.interfaces.EnhancedTestInterface;
import com.rysource.runner.EnhancedSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(EnhancedSuite.class)
@SuiteInformation(
        suiteName = "Example Suite",
        suiteDescription = "Examples on how to use the enhanced test runner",
        priority = SuiteInformation.SuitePriority.MEDIUM,
        suiteAcceptanceCriteria = {
                "All calculations must be correct"
        }
)
@Setup(
        reportType = Setup.ReportType.EXTENT_REPORT
)
@Suite.SuiteClasses({
        ExampleTest.class,
        ExampleTest_1_WithReporting.class,
        ExampleTest_2_WithReproting.class,
        ExampleTest_3_WithReporting.class
})
public class ExampleTestRunner implements EnhancedTestInterface {
    @Override
    public void onTestFinished(boolean result, String className, String methodName) {

    }

    @Override
    public void onTestStarted(String className, String methodName) {

    }

    @Override
    public void onTestFailure(String className, String methodName, String message, String stack) {

    }

    @Override
    public void onTestPassed(String className, String methodName) {

    }

    @Override
    public void onTestIgnored(String className, String methodName) {

    }
}

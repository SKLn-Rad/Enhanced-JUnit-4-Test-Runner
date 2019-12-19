import com.rysource.annotations.SuiteInformation;
import com.rysource.annotations.TestInformation;
import com.rysource.runner.EnhancedAssertion;
import org.junit.Test;

@SuiteInformation(
        suiteName = "Launch site Tests Set 2",
        suiteDescription = "Another set of tests to put the reporting solution through its paces",
        priority = SuiteInformation.SuitePriority.MEDIUM,
        suiteAcceptanceCriteria = {
                "Browser launches," +
                        "Correct site is loaded" +
                        "And validated"
        }
)

public class ExampleTest_2_WithReproting {
    @TestInformation(
            testName = "TR_2 Mixed Hard Results - Overall Pass",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
           expectedBehaviour = "Given My simple test Passes\n" +
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.HIGH,
            type = TestInformation.TestType.AUTOMATIC
    )
    @Test
    public void RPT_001_HardPASS() throws InterruptedException {

        EnhancedAssertion.hardAssertCondition("Test 1 : Step 1", true);
        EnhancedAssertion.hardAssertCondition("Test 1 : Step 2", true);
        EnhancedAssertion.hardAssertCondition("Test 1 : Step 3", true);
    }

    @TestInformation(
            testName = "TR_2 Simple Fail",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
            expectedBehaviour = "Given My simple test Fails\n" +
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.CRITICAL,
            type = TestInformation.TestType.AUTOMATIC
    )
    @Test
    public void RPT_002_SimpleFail() throws InterruptedException {

        EnhancedAssertion.softAssertCondition("Test 2 : Step 1", false);
    }

    @TestInformation(
            testName = "TR_2 Mixed Result - Over all Pass",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
            expectedBehaviour = "Given My test has soft Fails & Passes\n" +
                    "And a Hard Pass and a Hard Fail\n "+
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.HIGH,
            type = TestInformation.TestType.AUTOMATIC
    )
    @Test
    public void RPT_003_MultiStepFail() throws InterruptedException {


        EnhancedAssertion.hardAssertCondition("Test 2 : Step 1 - Hard Pass", true);
       EnhancedAssertion.softAssertCondition(true, "Test 2 : Step 2 - Soft Pass");
       EnhancedAssertion.softAssertCondition(false, "Test 2 : Step 3 - Soft fail");
        for(int i=0; i<=15; i++){
            Thread.sleep(1000);
            EnhancedAssertion.hardAssertCondition("Test 1 : Step " + i, true);
            //EnhancedLogging.debug(i+"s");
        }
        EnhancedAssertion.softAssertCondition(false, "Test 2 : Step 4 - Soft fail");
        EnhancedAssertion.softAssertCondition(false, "Test 2 : Step 5 - Soft fail");
        EnhancedAssertion.softAssertCondition(false, "Test 2 : Step 6 - Soft fail");

    }


}

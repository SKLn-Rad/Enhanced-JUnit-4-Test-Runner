import com.rysource.annotations.SuiteInformation;
import com.rysource.annotations.TestInformation;
import com.rysource.runner.EnhancedAssertion;
import com.rysource.runner.EnhancedLogging;
import org.junit.Test;

@SuiteInformation(
        suiteName = "Launch site Tests Set 1",
        suiteDescription = "Able to launch and check the successful launch of a website",
        priority = SuiteInformation.SuitePriority.HIGH,
        suiteAcceptanceCriteria = {
                "Browser launches," +
                        "Correct site is loaded" +
                        "And validated"
        }
)

public class ExampleTest_1_WithReporting {
    @TestInformation(
            testName = "Simple Pass",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
           expectedBehaviour = "Given My simple test Passes\n" +
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.HIGH,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 3
    )
    @Test
    public void RPT_001_SimplePASS() throws InterruptedException {

        EnhancedAssertion.hardAssertCondition("Test 1 : Step 1", true);
        Thread.sleep(1345);
        EnhancedLogging.debug("Slowing test to check timing counter results");
        for(int i=0; i<=45; i++){
            Thread.sleep(1000);
                       EnhancedAssertion.hardAssertCondition("Test 1 : Step " + i, true);
            //EnhancedLogging.debug(i+"s");
        }

        EnhancedLogging.debug("TEST 123");
        Thread.sleep(150);
        EnhancedLogging.testlog("In test info");
    }

    @TestInformation(
            testName = "Simple Fail",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
            expectedBehaviour = "Given My simple test Fails\n" +
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.MEDIUM,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 2
    )
    @Test
    public void RPT_002_HardFail() throws InterruptedException {
        Thread.sleep(1345);
        EnhancedAssertion.hardAssertCondition("Test 2 : Step 1", false);
        Thread.sleep(3000);
    }

    @TestInformation(
            testName = "Mixed Result",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
            expectedBehaviour = "Given My test has soft Fails & Passes\n" +
                    "And a Hard Pass and a Hard Fail\n "+
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.LOW,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 2
    )
    @Test
    public void RPT_003_MultiStepFail() throws InterruptedException {
        EnhancedAssertion.hardAssertCondition("Test 2 : Step 1 - Hard Pass", true);
        Thread.sleep(1345);
        EnhancedAssertion.softAssertCondition(true, "Test 2 : Step 2 - Soft Pass");
        Thread.sleep(1345);
        EnhancedAssertion.softAssertCondition(false, "Test 2 : Step 3 - Soft fail");
        Thread.sleep(1345);
        //EnhancedAssertion.hardAssertCondition("Test 2 : Step 4 :  Hard Fail", false);
    }


}

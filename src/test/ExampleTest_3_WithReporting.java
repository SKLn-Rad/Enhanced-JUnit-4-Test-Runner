import com.rysource.annotations.SuiteInformation;
import com.rysource.annotations.TestInformation;
import com.rysource.runner.EnhancedAssertion;
import org.junit.Test;

@SuiteInformation(
        suiteName = "Launch site Tests Set 3",
        suiteDescription = "Final set of test for testing reporter. \n Now handling new lines in test Suite description?",
        priority = SuiteInformation.SuitePriority.CRITICAL,
        suiteAcceptanceCriteria = {
                "Browser launches," +
                        "Correct site is loaded" +
                        "And validated"
        }
)

public class ExampleTest_3_WithReporting {
    @TestInformation(
            testName = "002 TR_3 Hard Results - Overall Pass",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
            expectedBehaviour = "Given My simple test Passes\n" +
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.CRITICAL,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 2
    )
    @Test
    public void RPT_002_HardPASS() throws InterruptedException {

        EnhancedAssertion.hardAssertCondition("Test 1 : Step 1", true);
        EnhancedAssertion.hardAssertCondition("Test 1 : Step 2", true);
        EnhancedAssertion.hardAssertCondition("Test 1 : Step 3", true);
    }

    @TestInformation(
            testName = "001 -TR_3 Simple Pass",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
            expectedBehaviour = "Given My simple test Fails\n" +
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.HIGH,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 1
    )
    @Test
    public void RPT_001_HardFail() throws InterruptedException {

        EnhancedAssertion.softAssertCondition("Test 2 : Step 1", true);
    }

    @TestInformation(
            testName = "003 - TR_3 Pass Result - Over all Pass, Long Test Name, Long test Detail, Lots of reslults",
            testDescription = "As a tester\n" +
                    "I want to Generate a report\n" +
                    "So that I can see the appropriate results",
            expectedBehaviour = "Given My test has soft Passes\n" +
                    "And a Hard Pass and a no Fails\n " +
                    "And one of the tests has a stupid long test detail for some reason" +
                    "When I view the report\n" +
                    "Then the result is correctly shown\n",
            priority = TestInformation.TestPriority.CRITICAL,
            type = TestInformation.TestType.AUTOMATIC,
            testRunOrder = 3
    )

    @Test
    public void RPT_003_MultiStepFail() throws InterruptedException {
int n =0;
        EnhancedAssertion.hardAssertCondition("Test 3 : Step 1 - Hard Pass \n And now I am entering a fairly long description to see what will happen on the report \n " +
                "Aaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaa Aaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaa Aaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaaAaaaaaaaaaaaaa a aaaa  a a a a aaaa aaaaa", true);
        EnhancedAssertion.softAssertCondition(true, "Test 3 : Step 2 - Soft Pass");
        for(int i=3; i<=65; i++){
            Thread.sleep(1000);
            EnhancedAssertion.softAssertCondition("Test 3 : Step " + i, true);
            //EnhancedLogging.debug(i+"s");
            n=i;
        }
        EnhancedAssertion.softAssertCondition(true, "Test 3 : Step " + n+1+" - Soft pass");

    }


}

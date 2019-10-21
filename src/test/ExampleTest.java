import com.rysource.annotations.TestInformation;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleTest {

    @Test
    @TestInformation(
            testName = "t1",
            testDescription = "t11",
            expectedBehaviour = "fds",
            priority = TestInformation.TestPriority.MEDIUM,
            type = TestInformation.TestType.AUTOMATIC
    )
    public void testAddition() {
        Assert.assertTrue(1 + 1 == 2);
    }

    @Test
    @TestInformation(
            testName = "Test Subtraction",
            testDescription = "t11",
            expectedBehaviour = "fds",
            priority = TestInformation.TestPriority.MEDIUM,
            type = TestInformation.TestType.AUTOMATIC
    )
    public void testSubtraction() {
        Assert.assertTrue(1 + 1 == 2);
        Assert.assertTrue(4 + 3 == 2);
    }

    @Test
    @Ignore
    @TestInformation(
            testName = "t1",
            testDescription = "t11",
            expectedBehaviour = "fds",
            priority = TestInformation.TestPriority.MEDIUM,
            type = TestInformation.TestType.MANUAL
    )
    public void testDivision() {
        Assert.assertTrue(4 + 3 == 2);
    }

}

package testCases;

import base.BaseTest;
import driver.DriverManager;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void launchAppTest() {
        System.out.println(DriverManager.getDriver().getSessionId());
    }
}

package testCases;

import base.BaseTest;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void launchAppTest() {
        System.out.println(driver.getSessionId());
    }
}

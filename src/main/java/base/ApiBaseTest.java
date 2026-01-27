package base;

import org.testng.annotations.BeforeMethod;

public class ApiBaseTest {

    @BeforeMethod
    public void setupApi() {
        // Nothing to start (no driver, no appium)
        // but you can load tokens, env, headers here
    }
}

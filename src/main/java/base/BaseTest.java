package base;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import server.AppiumServerManager;

public class BaseTest {

    protected AppiumDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void startAppium() {
        AppiumServerManager.startServer();
    }

    @Parameters({"platform", "port"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(
            String platform,
            String port,
            ITestContext context
    ) throws Exception {

        // Initialize driver
        DriverFactory.initDriver(platform, Integer.parseInt(port));

        driver = DriverFactory.getDriver();
        context.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppium() {
        AppiumServerManager.stopServer();
    }
}

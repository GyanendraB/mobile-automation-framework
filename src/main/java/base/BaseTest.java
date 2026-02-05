package base;

import config.ConfigManager;
import driver.DriverCreator;
import driver.DriverFactory;
import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import server.AppiumServerManager;

public class BaseTest {

    // ---------------- SUITE SETUP ----------------
    @BeforeSuite(alwaysRun = true)
    public void startAppium() {

        if (ConfigManager.get("run.mode").equalsIgnoreCase("cloud")) {
            System.out.println("Running in CLOUD mode. Skipping local Appium start.");
            return;
        }

        AppiumServerManager.startServer();
    }

    // ---------------- TEST SETUP ----------------
    @Parameters({"platform", "port"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String platform, String port, ITestContext context) throws Exception {

        DriverCreator creator =
                DriverFactory.getDriverCreator(platform);

        AppiumDriver driver =
                creator.createDriver(Integer.parseInt(port));

        DriverManager.setDriver(driver);       // ThreadLocal storage
        context.setAttribute("driver", driver);
    }

    // ---------------- TEST TEARDOWN ----------------
    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        AppiumDriver driver = DriverManager.getDriver();

        if (driver != null) {
            driver.quit();
        }

        DriverManager.unload();   // Prevent ThreadLocal memory leak
    }

    // ---------------- SUITE TEARDOWN ----------------
    @AfterSuite(alwaysRun = true)
    public void stopAppium() {

        if (ConfigManager.get("run.mode").equalsIgnoreCase("cloud")) {
            System.out.println("Running in CLOUD mode. No local Appium to stop.");
            return;
        }

        AppiumServerManager.stopServer();
    }
}

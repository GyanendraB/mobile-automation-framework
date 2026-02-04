package base;

import config.ConfigManager;
import driver.DriverFactory;
import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import server.AppiumServerManager;
public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void startAppium() {
        // 🔥 Do NOT start Appium when running in cloud (BrowserStack / Sauce)
        if (ConfigManager.get("run.mode").equalsIgnoreCase("cloud")) {
            System.out.println("Running in CLOUD mode. Skipping local Appium start.");
            return;
        }
        AppiumServerManager.startServer();
    }

    @Parameters({"platform", "port"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String platform, String port, ITestContext context) throws Exception {

        AppiumDriver driver =
                DriverFactory.createDriver(platform, Integer.parseInt(port));

        DriverManager.setDriver(driver);     // 🔥 store per thread
        context.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }

        DriverManager.unload();   // 🔥 prevent memory leaks
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppium() {
        // 🔥 Do NOT stop anything in cloud mode
        if (ConfigManager.get("run.mode").equalsIgnoreCase("cloud")) {
            System.out.println("Running in CLOUD mode. No local Appium to stop.");
            return;
        }
        AppiumServerManager.stopServer();
    }
}

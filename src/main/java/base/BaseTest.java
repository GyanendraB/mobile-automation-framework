package base;

import driver.DriverFactory;
import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import server.AppiumServerManager;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void startAppium() {
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
        AppiumServerManager.stopServer();
    }
}

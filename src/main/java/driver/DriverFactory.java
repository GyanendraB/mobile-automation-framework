package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;

public class DriverFactory {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String platform, int port) throws Exception {

        URL serverUrl = new URL("http://127.0.0.1:" + port);

        if (platform.equalsIgnoreCase("android")) {

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("49d998a8");
            options.setPlatformName("Android");
            options.setPlatformVersion("11");
            options.setAutomationName("UiAutomator2");
            options.setAppPackage("me.timeto.app");
            options.setAppActivity("me.timeto.app.MainActivity");

            // Required for parallel Android runs
            options.setSystemPort(0); // let Appium auto-assign

            options.autoGrantPermissions();

            AppiumDriver baseDriver =
                    new AppiumDriver(serverUrl, options);

            // 🔥 Healenium wrapped driver
//            AppiumDriver healingDriver =
//                    (AppiumDriver) SelfHealingDriver.create(baseDriver);

            driver.set(baseDriver);

        } else if (platform.equalsIgnoreCase("ios")) {

            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName("iPhone 14");
            options.setPlatformName("iOS");
            options.setPlatformVersion("17.0");
            options.setBundleId("com.example.iosapp");

            // Required for parallel iOS runs
            options.setWdaLocalPort(8100 + (int) (Thread.currentThread().getId() % 100));

            AppiumDriver baseDriver =
                    new AppiumDriver(serverUrl, options);

//            // 🔥 Healenium wrapped driver
//            AppiumDriver healingDriver =
//                    (AppiumDriver) SelfHealingDriver.create(baseDriver);

            driver.set(baseDriver);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

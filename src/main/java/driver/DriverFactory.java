package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;

public class DriverFactory {

    public static AppiumDriver createDriver(String platform, int port) throws Exception {

        URL serverUrl = new URL("http://127.0.0.1:" + port);

        if (platform.equalsIgnoreCase("android")) {

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("49d998a8");
            options.setPlatformName("Android");
            options.setPlatformVersion("11");
            options.setAutomationName("UiAutomator2");
            options.setAppPackage("me.timeto.app");
            options.setAppActivity("me.timeto.app.MainActivity");
            options.autoGrantPermissions();
            options.setSystemPort(0);   // parallel-safe

            return new AppiumDriver(serverUrl, options);
        }

        else if (platform.equalsIgnoreCase("ios")) {

            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName("iPhone 14");
            options.setPlatformName("iOS");
            options.setPlatformVersion("17.0");
            options.setBundleId("com.example.iosapp");
            options.setWdaLocalPort(8100 + (int) (Thread.currentThread().getId() % 100));

            return new AppiumDriver(serverUrl, options);
        }

        throw new RuntimeException("Invalid platform: " + platform);
    }
}

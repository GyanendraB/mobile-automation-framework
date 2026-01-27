package driver;

import config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;

public class DriverFactory {

    public static AppiumDriver createDriver(String platform, int port) throws Exception {

        String runMode = ConfigManager.get("run.mode");

        URL serverUrl;

        if (runMode.equalsIgnoreCase("cloud")) {
            serverUrl = new URL(ConfigManager.get("cloud.url"));
        } else {
            serverUrl = new URL("http://127.0.0.1:" + port);
        }

        if (platform.equalsIgnoreCase("android")) {

            UiAutomator2Options options = new UiAutomator2Options();

            options.setDeviceName(ConfigManager.get("deviceName"));
            options.setPlatformName("Android");
            options.setPlatformVersion(ConfigManager.get("platformVersion"));
            options.setAppPackage(ConfigManager.get("appPackage"));
            options.setAppActivity(ConfigManager.get("appActivity"));
            options.setAutomationName("UiAutomator2");
            options.autoGrantPermissions();
            options.setSystemPort(0);

            return new AppiumDriver(serverUrl, options);
        }

        else if (platform.equalsIgnoreCase("ios")) {

            XCUITestOptions options = new XCUITestOptions();

            options.setDeviceName(ConfigManager.get("iosDeviceName"));
            options.setPlatformName("iOS");
            options.setPlatformVersion(ConfigManager.get("iosPlatformVersion"));
            options.setBundleId(ConfigManager.get("bundleId"));
            options.setWdaLocalPort(8100 + (int)(Thread.currentThread().getId() % 100));

            return new AppiumDriver(serverUrl, options);
        }

        throw new RuntimeException("Invalid platform: " + platform);
    }
}

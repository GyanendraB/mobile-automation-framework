package driver;

import config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;

public class DriverFactory {

    public static AppiumDriver createDriver(String platform, int port) throws Exception {

        String runMode = ConfigManager.get("run.mode");

        URL serverUrl;

        if (runMode.equalsIgnoreCase("cloud")) {

            String user = System.getenv("BROWSERSTACK_USER");
            String key = System.getenv("BROWSERSTACK_KEY");

            serverUrl = new URL("https://" + user + ":" + key + "@hub-cloud.browserstack.com/wd/hub");

        } else {
            serverUrl = new URL("http://127.0.0.1:" + port);
        }

        if (platform.equalsIgnoreCase("android")) {

            UiAutomator2Options options = new UiAutomator2Options();

            options.setDeviceName(ConfigManager.get("deviceName"));
            options.setPlatformName("Android");
            options.setPlatformVersion(ConfigManager.get("platformVersion"));
            options.setApp(System.getenv("BROWSERSTACK_APP"));
            options.setAutomationName("UiAutomator2");

            options.setCapability("project", "TimeTo Mobile");
            options.setCapability("build", "GitHub CI");
            options.setCapability("name", "Android Smoke Test");

            return new AppiumDriver(serverUrl, options);
        }

        throw new RuntimeException("Invalid platform");
    }
}

package driver;

import config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

            if (runMode.equalsIgnoreCase("cloud")) {

                Map<String, Object> bstackOptions = new HashMap<>();
                bstackOptions.put("userName", System.getenv("BROWSERSTACK_USER"));
                bstackOptions.put("accessKey", System.getenv("BROWSERSTACK_KEY"));
                bstackOptions.put("deviceName", ConfigManager.get("deviceName"));
                bstackOptions.put("osVersion", ConfigManager.get("platformVersion"));
                bstackOptions.put("projectName", "TimeTo Mobile");
                bstackOptions.put("buildName", "GitHub CI");
                bstackOptions.put("sessionName", "Android Smoke");

                options.setCapability("bstack:options", bstackOptions);
                options.setCapability("app", System.getenv("BROWSERSTACK_APP"));
                options.setCapability("platformName", "Android");

            } else {
                options.setDeviceName(ConfigManager.get("deviceName"));
                options.setPlatformName("Android");
                options.setPlatformVersion(ConfigManager.get("platformVersion"));
                options.setAppPackage(ConfigManager.get("appPackage"));
                options.setAppActivity(ConfigManager.get("appActivity"));
                options.setApp(ConfigManager.get("appPath"));
                options.autoGrantPermissions();
                options.setSystemPort(0);
            }

            return new AppiumDriver(serverUrl, options);
        }

        throw new RuntimeException("Invalid platform");
    }
}

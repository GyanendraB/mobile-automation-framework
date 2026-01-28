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
        URL serverUrl = getServerUrl(runMode, port);

        if (platform.equalsIgnoreCase("android")) {
            return createAndroidDriver(runMode, serverUrl);
        }

        throw new RuntimeException("Unsupported platform: " + platform);
    }

    // ---------------- SERVER URL ----------------
    private static URL getServerUrl(String runMode, int port) throws Exception {

        if (runMode.equalsIgnoreCase("cloud")) {
            String user = System.getenv("BROWSERSTACK_USER");
            String key = System.getenv("BROWSERSTACK_KEY");
            return new URL("https://" + user + ":" + key +
                    "@hub-cloud.browserstack.com/wd/hub");
        }

        // LOCAL Appium
        return new URL("http://127.0.0.1:" + port);
    }

    // ---------------- ANDROID DRIVER ----------------
    private static AppiumDriver createAndroidDriver(String runMode, URL serverUrl) {

        UiAutomator2Options options = new UiAutomator2Options();

        if (runMode.equalsIgnoreCase("cloud")) {

            // ---------- BrowserStack ----------
            Map<String, Object> bstackOptions = new HashMap<>();
            bstackOptions.put("deviceName", "Samsung Galaxy S22");   // known-good device
            bstackOptions.put("osVersion", "12");
            bstackOptions.put("projectName", "TimeTo Mobile");
            bstackOptions.put("buildName", "GitHub CI");
            bstackOptions.put("sessionName", "Android Smoke Test");

            options.setCapability("bstack:options", bstackOptions);
            options.setApp(System.getenv("BROWSERSTACK_APP"));

        } else {

            // ---------- LOCAL (REAL DEVICE) ----------
            options.setDeviceName("49d998a8");          // your real device ID
            options.setPlatformVersion("11");
            options.setAppPackage("me.timeto.app");
            options.setAppActivity("me.timeto.app.MainActivity");
            options.setAutomationName("UiAutomator2");
            options.autoGrantPermissions();

            // Parallel-safe local execution
            options.setSystemPort(0);
        }

        return new AppiumDriver(serverUrl, options);
    }
}

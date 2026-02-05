package driver.android;

import driver.DriverCreator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CloudAndroidDriverCreator implements DriverCreator {

    @Override
    public AppiumDriver createDriver(int port) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("deviceName", "Samsung Galaxy S22");
        bstackOptions.put("osVersion", "12");
        bstackOptions.put("projectName", "TimeTo Mobile");
        bstackOptions.put("buildName", "GitHub CI");
        bstackOptions.put("sessionName", "Android Smoke Test");

        options.setCapability("bstack:options", bstackOptions);
        options.setApp(System.getenv("BROWSERSTACK_APP"));

        String user = System.getenv("BROWSERSTACK_USER");
        String key = System.getenv("BROWSERSTACK_KEY");

        URL serverUrl = new URL(
                "https://" + user + ":" + key + "@hub-cloud.browserstack.com/wd/hub"
        );

        return new AppiumDriver(serverUrl, options);
    }
}

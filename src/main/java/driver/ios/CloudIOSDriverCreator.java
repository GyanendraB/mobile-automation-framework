package driver.ios;

import driver.DriverCreator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CloudIOSDriverCreator implements DriverCreator {

    @Override
    public AppiumDriver createDriver(int port) throws Exception {

        XCUITestOptions options = new XCUITestOptions();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("deviceName", "iPhone 14");
        bstackOptions.put("osVersion", "16");
        bstackOptions.put("projectName", "TimeTo Mobile");
        bstackOptions.put("buildName", "GitHub CI");
        bstackOptions.put("sessionName", "iOS Smoke Test");

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

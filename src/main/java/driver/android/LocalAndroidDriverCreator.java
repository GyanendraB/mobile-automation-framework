package driver.android;

import driver.DriverCreator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;

public class LocalAndroidDriverCreator implements DriverCreator {

    @Override
    public AppiumDriver createDriver(int port) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("49d998a8");
        options.setPlatformVersion("11");
        options.setAppPackage("me.timeto.app");
        options.setAppActivity("me.timeto.app.MainActivity");
        options.setAutomationName("UiAutomator2");
        options.autoGrantPermissions();
        options.setSystemPort(0);

        URL serverUrl = new URL("http://127.0.0.1:" + port);
        return new AppiumDriver(serverUrl, options);
    }
}

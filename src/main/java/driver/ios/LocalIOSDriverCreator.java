package driver.ios;

import driver.DriverCreator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;

public class LocalIOSDriverCreator implements DriverCreator {

    @Override
    public AppiumDriver createDriver(int port) throws Exception {

        XCUITestOptions options = new XCUITestOptions();

        // ---------- Simulator example ----------
        options.setDeviceName("iPhone 14");
        options.setPlatformVersion("16.4");
        options.setAutomationName("XCUITest");

        // If real device, uncomment:
        // options.setUdid("YOUR_REAL_DEVICE_UDID");

        options.setBundleId("com.example.myiosapp");
        options.setWdaLocalPort(0);   // 🔥 parallel-safe
        options.setUseNewWDA(true);

        URL serverUrl = new URL("http://127.0.0.1:" + port);
        return new AppiumDriver(serverUrl, options);
    }
}

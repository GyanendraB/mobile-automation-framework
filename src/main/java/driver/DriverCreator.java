package driver;

import io.appium.java_client.AppiumDriver;

public interface DriverCreator {
    AppiumDriver createDriver(int port) throws Exception;
}

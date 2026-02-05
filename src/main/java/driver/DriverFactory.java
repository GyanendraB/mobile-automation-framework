package driver;

import config.ConfigManager;
import driver.android.CloudAndroidDriverCreator;
import driver.android.LocalAndroidDriverCreator;

public class DriverFactory {

    public static DriverCreator getDriverCreator(String platform) {

        String runMode = ConfigManager.get("run.mode");

        if (platform.equalsIgnoreCase("android")) {

            if (runMode.equalsIgnoreCase("cloud")) {
                return new CloudAndroidDriverCreator();
            }

            if (runMode.equalsIgnoreCase("local")) {
                return new LocalAndroidDriverCreator();
            }
        }

        throw new RuntimeException(
                "Unsupported configuration: " + platform + " / " + runMode
        );
    }
}

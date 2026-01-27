package server;

import config.ConfigManager;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class AppiumServerManager {

    private static AppiumDriverLocalService service;

    public static void startServer() {

        // 🔥 Do NOT start Appium when running in cloud (BrowserStack / Sauce)
        if (ConfigManager.get("run.mode").equalsIgnoreCase("cloud")) {
            System.out.println("Running in CLOUD mode. Skipping local Appium start.");
            return;
        }

        if (service == null || !service.isRunning()) {

            AppiumServiceBuilder builder = new AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                    .withAppiumJS(new File("C:\\Users\\Admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));

            service = AppiumDriverLocalService.buildService(builder);
            service.start();
            System.out.println("Local Appium Server Started");
        }
    }

    public static void stopServer() {

        // 🔥 Do NOT stop anything in cloud mode
        if (ConfigManager.get("run.mode").equalsIgnoreCase("cloud")) {
            System.out.println("Running in CLOUD mode. No local Appium to stop.");
            return;
        }

        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Local Appium Server Stopped");
        }
    }
}

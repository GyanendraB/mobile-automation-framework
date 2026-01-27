package server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class AppiumServerManager {

    private static AppiumDriverLocalService service;

    public static void startServer() {

        if (service == null || !service.isRunning()) {

            AppiumServiceBuilder builder = new AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                    .withAppiumJS(new File("C:\\Users\\Admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));

            service = AppiumDriverLocalService.buildService(builder);
            service.start();
        }
    }

    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}

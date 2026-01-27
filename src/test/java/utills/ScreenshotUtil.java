package utills;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(AppiumDriver driver, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                    .format(new Date());

            // absolute path for saving
            String screenshotsDir = System.getProperty("user.dir")
                    + "/reports/screenshots/";
            new File(screenshotsDir).mkdirs();

            String fileName = testName + "_" + timestamp + ".png";
            String absolutePath = screenshotsDir + fileName;

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), new File(absolutePath).toPath());

            // 🔥 RETURN RELATIVE PATH (this fixes broken image)
            return "screenshots/" + fileName;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

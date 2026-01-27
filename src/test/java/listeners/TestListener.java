package listeners;

import com.aventstack.extentreports.*;
import io.appium.java_client.AppiumDriver;
import org.testng.*;
import utills.ExtentManager;
import utills.ScreenshotUtil;


public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed ✅");
    }
    @Override
    public void onTestFailure(ITestResult result) {

        AppiumDriver driver =
                (AppiumDriver) result.getTestContext()
                        .getAttribute("driver");

        String screenshotPath =
                ScreenshotUtil.takeScreenshot(driver,
                        result.getMethod().getMethodName());

        test.get().fail(result.getThrowable());

        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    }


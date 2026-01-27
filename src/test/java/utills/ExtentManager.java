package utills;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                    .format(new Date());

            String reportPath =
                    "reports/ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Mobile Automation Report");
            reporter.config().setDocumentTitle("Appium Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}

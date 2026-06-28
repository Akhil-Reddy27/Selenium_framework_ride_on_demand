package Globalproperties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Extentreports {


    public static ExtentReports getreports() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(dtf);
        String path = System.getProperty("user.dir") + "//reports//ExtentReports_" + timestamp + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("TestingReport_1");
        reporter.config().setDocumentTitle("Ride_on_demand_TestReport");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Akhil Reddy");
        return extent;
    }
}

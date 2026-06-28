package Utils;

import Globalproperties.Extentreports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;


public class Listener implements ITestListener {

    ExtentReports extent = Extentreports.getreports();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Assign directly to a local variable to prevent thread cross-talk
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        // Pull the active driver directly from the TestNG Context attributes safely
        ITestContext context = result.getTestContext();
        WebDriver activeDriver = (WebDriver) context.getAttribute("WebDriver");

        //If context didn't catch it, try extracting it straight from the running test instance
        if (activeDriver == null) {
            try {
                activeDriver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            } catch (Exception e) {
                System.out.println("Could not obtain WebDriver via reflection: " + e.getMessage());
            }
        }

        //Only takes a screenshot if our driver extraction succeeded
        if (activeDriver != null) {
            String filepath = null;
            try {
                baseTest testInstance = (baseTest) result.getInstance();
                filepath = testInstance.getScreenshot(result.getMethod().getMethodName(), activeDriver);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (filepath != null) {
                extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
            }
        } else {
            System.out.println("Screenshot skipped: WebDriver instance came back null.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }


    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}
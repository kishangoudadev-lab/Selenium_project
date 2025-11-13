package com.myStore.test.listener;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.mystore.base.BaseClass;
import com.mystore.utility.ExtentManager;
import com.mystore.utility.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ExtentTestListener implements ITestListener {


    private static ExtentReports extent = ExtentManager.getReporter();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>(); // cretating test case entries in the report and udpate status of the test method
    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        Object testClass = result.getInstance();
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver").get(testClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String screenshotPath = ScreenshotUtil.captureAndReturnPath(driver, result.getMethod().getMethodName());
        test.get().addScreenCaptureFromPath(screenshotPath);
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
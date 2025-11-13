package com.myStore.test.listener;

import com.mystore.base.BaseClass;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.mystore.utility.ScreenshotUtil;


public class TestListener extends BaseClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        ScreenshotUtil.captureScreenshot(driver, testName);
    }
}

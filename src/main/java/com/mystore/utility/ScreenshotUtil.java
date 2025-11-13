package com.mystore.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots during test execution.
 * Screenshots are stored inside project root -> /screenshots/ folder.
 */
public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/screenshots/";

    /**
     * Capture screenshot and save it with test name + timestamp.
     *
     * @param driver   WebDriver instance
     * @param testName Name of the test or step
     */
    public static void captureScreenshot(WebDriver driver, String testName) {
        saveScreenshot(driver, testName, false);
    }

    /**
     * Capture screenshot, save it, and return file path (useful for reports).
     *
     * @param driver   WebDriver instance
     * @param testName Name of the test or step
     * @return Absolute path of the saved screenshot
     */
    public static String captureAndReturnPath(WebDriver driver, String testName) {
        return saveScreenshot(driver, testName, true);
    }

    /**
     * Internal method to capture and save screenshot.
     */
    private static String saveScreenshot(WebDriver driver, String testName, boolean returnPath) {
        if (!(driver instanceof TakesScreenshot)) {
            System.out.println("⚠️ Driver does not support screenshot capturing!");
            return null;
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timeStamp + ".png";

        File destiny = new File(SCREENSHOT_DIR + fileName);

        try {
            // Ensure directory exists
            destiny.getParentFile().mkdirs();

            FileUtils.copyFile(src, destiny);
            System.out.println("✅ Screenshot saved at: " + destiny.getAbsolutePath());

            return returnPath ? destiny.getAbsolutePath() : null;
        } catch (IOException e) {
            System.out.println("❌ Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
}

package com.mystore.actiondriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Action {

    public static Logger log = LoggerFactory.getLogger(Action.class);

    public static void click(WebDriver driver, WebElement element) {
        log.debug("when clicked on the element: {}", element);
        onClickElement(driver, element, 10);  // optional waiting helper
        element.click();
        log.info("click on the element: {}", element);
    }

    public static void typing(WebElement element, String text) {
        log.debug("typing '{}' content into element: {}", text, element);
        element.clear();
        element.sendKeys(text);
        log.info("sent keys '{}',element", text);
    }


    // to check whether element is diplayed or not
    public static boolean isDisplayed(WebDriver webDriver, WebElement element) {

        boolean display = false;
        try {
            display = element.isDisplayed();
            log.debug("element displayed:{}", display);
        } catch (Exception e) {
            log.error("element is not displayed", e);
        }
        return display;
    }

    // it is ragarding the implicit wait
    public static void implicitWait(WebDriver driver, int time) {
        log.info(" set the implicit wait to  {} seconds", time);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    //  time taken to page load
    public static void pageLoad(WebDriver driver, int time) {
        log.info("it waits the page upto {} seconds", time);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
    }

    // after click on the element
    public static void onClickElement(WebDriver driver, WebElement element, int time) {
        log.debug("wait upto {} seconds  certain time: {}", time, element);
        new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOf(element));
        log.info("element is now clickable: {}", element);
    }

    public static void hover(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .pause(Duration.ofSeconds(5))  // hover for 5 seconds
                .perform();
    }

    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }
    public static void typingWithDelay(WebElement element, String text, long delayInMillis) {
        element.clear();
        for (char c : text.toCharArray()) {
            element.sendKeys(Character.toString(c));
            try {
                Thread.sleep(delayInMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }




}
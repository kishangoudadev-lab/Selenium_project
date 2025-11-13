package com.mystore.base;


import com.mystore.actiondriver.Action;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseClass {

    public static Properties prop;
    public static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseClass.class);

    public static WebDriver getDriver() {
        return driver;
    }

    public void loadConfig(){

        try{
            prop = new Properties();
            FileInputStream inp = new FileInputStream(System.getProperty("user.dir") + File.separator + "Configuration" + File.separator + "config.properties");
            prop.load(inp);
            logger.info("properties loaded successfully");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void launchApp(){

        if(prop == null){
            throw new IllegalStateException("properties are not initialized properly");
        }

        String browserName = prop.getProperty("browser");
        if(browserName.contains("chrome")){
            WebDriverManager.chromedriver().setup();
            driver= new ChromeDriver();
            driver.manage().window().maximize();
        }else if(browserName.contains("FireFox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }else {
            throw new RuntimeException("invalid browser" + browserName);
        }

        Action.implicitWait(driver,10);
        Action.pageLoad(driver,10);
        driver.get(prop.getProperty("url"));
        logger.info("it launched on the browser : {}",browserName);


    }


}

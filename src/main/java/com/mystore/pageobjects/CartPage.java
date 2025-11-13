package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class CartPage extends BaseClass{
    public static final Logger log = LoggerFactory.getLogger(HomePage.class);

    @FindBy(xpath = "//img[@alt='Cart']")
    private WebElement cartIcon;

    public CartPage(){
        PageFactory.initElements(driver,this);
        log.debug("initializing the Cart elements");
    }
    public boolean validateLogo(){
        log.info("Validate the flipkart Cart logo");
        boolean isLogo = Action.isDisplayed(driver, cartIcon);
        log.info("Logo is visible {}", isLogo);
        return isLogo;
    }

    public String testCartButton() {
        Action.click(driver,cartIcon);
        Action.implicitWait(driver,5);
        String url = driver.getCurrentUrl();
        return url;
    }
}

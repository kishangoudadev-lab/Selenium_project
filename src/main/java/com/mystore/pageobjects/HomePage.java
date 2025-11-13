package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.Duration;

public class HomePage extends BaseClass {
     public static final Logger log = LoggerFactory.getLogger(HomePage.class);

     @FindBy(xpath = "//img[@title ='Flipkart']")
     WebElement flipKartLogo;

     @FindBy(xpath = "//input[@type='text']")
     WebElement searchBar;

     @FindBy(css = "button._2iLD__")
     WebElement searchButton;

     @FindBy(css = "a[title = 'Login']")
     WebElement loginButton;

     @FindBy(css = "a[title = 'Cart']")
     WebElement cartPage;

     @FindBy(css = "ul._3YjYK7.ecs1XG")
     WebElement loginDropDown;

    @FindBy(css = "img[alt='Instagram']")
    private WebElement instagramIcon;

     public HomePage(){
        PageFactory.initElements(driver,this);
        log.debug("initializing the home elements");
    }
     public boolean validateLogo(){
       log.info("Validate the flipkart logo");
       boolean isLogo = Action.isDisplayed(driver, flipKartLogo);
       log.info("Logo is visible {}", isLogo);
       return isLogo;
    }

    public void searchBar(String s) {
         log.info("Search the product {}",s);
         Action.typing(searchBar,s);
         Action.click(driver,searchButton);
    }

    public boolean searchInUrl(String s) {
         String url = driver.getCurrentUrl();
        log.info("url of curr page: {}",url);
         return url.contains(s);
    }

    public boolean loginButton() {
         Action.click(driver,loginButton);
         String s = driver.getCurrentUrl();
         log.info("url of the curr page : {} ", s);
         return s.contains("login");
    }

    public boolean viewCartButton() {
         Action.click(driver,cartPage);
         String url = driver.getCurrentUrl();
         String lowerCase = url.toLowerCase();
         log.info("url of the curr page : {} ", url);
         return lowerCase.contains("cart");
    }

    public boolean loginHoverList() {
         Action.hover(driver,loginButton);
         boolean res = Action.isDisplayed(driver, loginDropDown);
         return res;
    }

    public boolean pageTitle(String s) {
         String title = Action.getTitle(driver);
         log.info("This is the title :- {}", title);
         return title.equals(s);
    }

    public void loginButtonclick() {
         Action.click(driver,loginButton);
    }

    public boolean footerLinkCheck() {
        Action.click(driver,instagramIcon);
        Action.implicitWait(driver, 10);
        return driver.getCurrentUrl().contains("instagram");
    }
}

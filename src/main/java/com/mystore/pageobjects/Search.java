package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class Search extends BaseClass {
    public static final Logger log = LoggerFactory.getLogger(HomePage.class);

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchBox;

    @FindBy(css = "ul._1sFryS._2x2Mmc li._3D0G9a")
    List<WebElement> searchSuggestions;

    @FindBy(xpath = "//a[contains(@href, '/p/')]")
    List<WebElement> products;

    public Search(){
        PageFactory.initElements(driver,this);
        log.debug("Started Search");
    }


    public boolean searchSuggestion() throws InterruptedException {
        clickSearchBox();
        Thread.sleep(5000);
        for(WebElement ele : searchSuggestions){
            if(Action.isDisplayed(driver,ele)){
                return true;
            }
        }
        return false;
    }

    public void clickSearchBox() {
        Action.click(driver,searchBox);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
    }

    public boolean searchProduct(String productName) {
        Action.click(driver, searchBox);
        Action.typing(searchBox, productName);
        clickSearchBox();
        System.out.println("Products count: " + products.size());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(products));

        for (WebElement ele : products) {
            System.out.println("Product found: " + ele.getText());
            if (ele.getText().toLowerCase().contains(productName.toLowerCase())) {
                System.out.println("✅ Match found: " + ele.getText());
                return true;
            }
        }
        System.out.println("❌ No matching product found for: " + productName);
        return false;
    }

    public boolean visibility() {
        return Action.isDisplayed(driver,searchBox);
    }
}

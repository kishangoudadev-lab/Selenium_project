package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BaseClass {
    public static final Logger log = LoggerFactory.getLogger(HomePage.class);
    @FindBy(css = "a.azBkHf")
    WebElement sighUp;

    @FindBy(css = "button.QqFHMw")
    WebElement requestOtpBtn;

    @FindBy(xpath = "//input[contains(@class, 'r4vIwl')]")
    WebElement enterValue;

    @FindBy(css = "span.llBOFA span")
    private WebElement invalidLoginMsg;

    @FindBy(xpath = "//a[contains(@href,'/pages/terms')]")
    private WebElement termsOfUseLink;

    @FindBy(xpath = "//a[contains(@href,'/pages/privacypolicy')]")
    private WebElement privacyPolicyLink;

    public LoginPage() {
        PageFactory.initElements(driver, this);
        log.debug("initializing the Login elements");
    }

    public boolean clickOnCreateAccount() {
        Action.click(driver,sighUp);
        String url = driver.getCurrentUrl();
        boolean res = url.contains("signup");
        return res;
    }

    public String invalidDetails() {
        Action.click(driver, enterValue);  // focus
        Action.typingWithDelay(enterValue, "cnfivnbfnfdv", 500); // 500ms per char
        Action.click(driver, requestOtpBtn);
        return invalidLoginMsg.getText();
    }

    public boolean checkTermsAndPolicy() {
        Action.click(driver, termsOfUseLink);
        boolean res1 = driver.getCurrentUrl().contains("terms");
        driver.navigate().back();
        Action.implicitWait(driver,5);
        Action.click(driver, privacyPolicyLink);
        boolean res2 = driver.getCurrentUrl().contains("privacypolicy");
        return res1 && res2;
    }
}

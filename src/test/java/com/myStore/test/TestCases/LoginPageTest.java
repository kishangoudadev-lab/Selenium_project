package com.myStore.test.TestCases;

import com.myStore.test.listener.ExtentTestListener;
import com.myStore.test.listener.TestListener;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class, ExtentTestListener.class})
public class LoginPageTest extends BaseClass {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        loadConfig();
        launchApp();
    }

    @AfterMethod
    public void close(){
        driver.quit();
    }

    @Test
    public void sighUpButton(){
        homePage = new HomePage();
        loginPage = new LoginPage();
        homePage.loginButtonclick();
        boolean res = loginPage.clickOnCreateAccount();
        Assert.assertTrue(res);
    }

    @Test
    public void invalidDetails(){
        homePage = new HomePage();
        loginPage = new LoginPage();
        homePage.loginButtonclick();
        String s = loginPage.invalidDetails();
        Assert.assertEquals(s, "Please enter valid Email ID/Mobile number");
    }

    @Test
    public void termsAndPrivacy(){
        homePage = new HomePage();
        loginPage = new LoginPage();
        boolean res = loginPage.checkTermsAndPolicy();
        Assert.assertTrue(res);
    }
}

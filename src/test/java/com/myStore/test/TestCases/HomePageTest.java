package com.myStore.test.TestCases;

import com.myStore.test.listener.ExtentTestListener;
import com.myStore.test.listener.TestListener;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class, ExtentTestListener.class})
public class HomePageTest  extends BaseClass {
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        loadConfig();
        launchApp();
    }
    @AfterMethod
    public void setDown(){
        driver.quit();
    }

    @Test
    public void validateLogo(){
        homePage = new HomePage();
        boolean result = homePage.validateLogo();
        Assert.assertTrue(result);
    }

    @Test
    public void searchText(){
        homePage = new HomePage();
        homePage.searchBar("Iphone 17");
    }

    @Test
    public void searchInProductUrl(){
        homePage = new HomePage();
        homePage.searchBar("laptop");
        boolean result = homePage.searchInUrl("laptop");
        Assert.assertTrue(result);
    }

    @Test
    public void testLoginButton(){
        homePage = new HomePage();
        boolean res = homePage.loginButton();
        Assert.assertTrue(res);
    }

    @Test
    public void testViewCartButton(){
        homePage = new HomePage();
        boolean res  = homePage.viewCartButton();
        Assert.assertTrue(res);
    }

    @Test
    public void testLoginDropDown(){
        homePage = new HomePage();
        boolean res = homePage.loginHoverList();
        Assert.assertTrue(res);
    }

    @Test
    public void checkTitleOfThePage(){
        homePage = new HomePage();
        boolean res  = homePage.pageTitle("Online Shopping India Mobile, Cameras, Lifestyle & more Online @ Flipkart.com");
        Assert.assertTrue(res);
    }

    @Test
    public void footerLinks(){
        homePage  = new HomePage();
        boolean res = homePage.footerLinkCheck();
        Assert.assertTrue(res);
    }
}

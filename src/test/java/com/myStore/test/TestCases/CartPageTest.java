package com.myStore.test.TestCases;

import com.myStore.test.listener.ExtentTestListener;
import com.myStore.test.listener.TestListener;
import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.CartPage;
import com.mystore.pageobjects.HomePage;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class, ExtentTestListener.class})
public class CartPageTest extends BaseClass {
    HomePage homePage;
    CartPage cartPage;
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
    public void validateCartLogo(){
        cartPage = new CartPage();
        boolean result = cartPage.validateLogo();
        Assert.assertTrue(result);
    }

    @Test
    public void testCartButton(){
        cartPage = new CartPage();
        String res = cartPage.testCartButton();
        Assert.assertTrue(res.contains("viewcart"));
    }

}

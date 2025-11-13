package com.myStore.test.TestCases;

import com.myStore.test.listener.ExtentTestListener;
import com.myStore.test.listener.TestListener;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.Search;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class, ExtentTestListener.class})
public class SearchPageTest extends BaseClass {
    LoginPage loginPage;
    HomePage homePage;
    Search search;
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
    public void searchBarVisibility(){
        search = new Search();
        boolean res = search.visibility();
        Assert.assertTrue(res);
    }

    @Test
    public void emptySearch() {
        search = new Search();
        String beforeUrl = driver.getCurrentUrl();
        search.clickSearchBox();
        String afterUrl = driver.getCurrentUrl();
        Assert.assertEquals(afterUrl, beforeUrl, "❌ Search triggered even when input was empty");
    }

    @Test
    public void productSearch() throws InterruptedException {
        search = new Search();
        boolean result = search.searchProduct("vivo");
        Assert.assertTrue(result);
    }

    @Test
    public void checkSuggestion() throws InterruptedException {
        search = new Search();
        boolean res = search.searchSuggestion();
        Assert.assertTrue(res);
    }
}

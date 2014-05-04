package com.blog.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 13-12-12
 * Time: 下午8:50
 * To change this template use File | Settings | File Templates.
 */
public class SearchPage {
    private WebDriver driver;

    @FindBy(xpath = "//html/body/div/div/section/div/header/h1")
    @CacheLookup
    private WebElement result;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkResult() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertEquals("Search Results for: 宗刚", result.getText());
        System.out.println("Search correctly!");
    }
}

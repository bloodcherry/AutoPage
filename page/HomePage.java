package com.blog.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 13-12-15
 * Time: 下午7:35
 * To change this template use File | Settings | File Templates.
 */
public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//html/body/div[2]/div/ul/li[2]/a")
    @CacheLookup
    private WebElement halflight;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdminPage go() {
        halflight.click();
        return new AdminPage(driver);
    }
}
